document.getElementById('searchForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    // Показываем анимацию загрузки
    const loadingSwal = Swal.fire({
        title: 'Загрузка данных',
        html: 'Пожалуйста, подождите...',
        allowOutsideClick: false,
        didOpen: () => {
            Swal.showLoading();
        }
    });

    try {
        const formData = new FormData(this);
        const payload = {
            name: formData.get("name"),
            surname: formData.get("surname"),
            patronymic: formData.get("patronymic"),
            passport: formData.get("passport")
        };

        const res = await fetch('http://localhost:8080/api/center/find-by-passport-data', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(payload)
        });

        await loadingSwal.close();

        if (!res.ok) {
            let errorMessage = 'На сервере произошла ошибка';
            let errorDetails = '';

            try {
                const errorData = await res.json();
                if (errorData.detail) {
                    errorMessage = errorData.detail;
                } else if (errorData.title) {
                    errorMessage = errorData.title;
                }
                if (Array.isArray(errorData.errors)) {
                    errorDetails = errorData.errors.join('\n');
                }

            } catch (e) {
                console.error('Ошибка парсинга ответа сервера:', e);
            }
            showError(errorMessage, errorDetails);
            return;
        }

        const data = await res.json();

        if(data.contracts.length === 0) {
            showWarning('Кредитная история для заданного пользователя не найдена');
            return;
        }

        showSuccess("Данные успешно получены");
        displayResult(data);

    } catch (error) {
        await loadingSwal.close();
        console.error('Ошибка при выполнении запроса:', error);
        showError('Произошла ошибка при соединении с сервером');
    }
});

function showSuccess(message, callback) {
    Swal.fire({
        icon: 'success',
        title: 'Успех!',
        text: message,
        confirmButtonText: 'ОК'
    }).then((result) => {
        if (result.isConfirmed && callback) {
            callback();
        }
    });
}

function showWarning(message) {
    Swal.fire({
        icon: 'warning',
        title: 'Внимание',
        text: message,
        confirmButtonText: 'Понятно',
    });
}

function showError(message,details) {
    Swal.fire({
        icon: 'error',
        title: 'Ошибка',
        text: `${message}\n${details}`,
        confirmButtonText: 'ОК'
    });
}

function displayResult(data) {
    const container = document.getElementById('result');
    container.innerHTML = '';

    const user = data.user;
    container.innerHTML += `
        <h2>Пользователь</h2>
        <p><strong>${user.surname} ${user.name} ${user.patronymic}</strong></p>
        <p>Email: ${user.email}</p>
        <p>Паспорт: ${user.passport}</p>
        <p>ИНН: ${user.inn}</p>
      `;
    data.contracts.forEach(contract => {
        const contractHtml = `
          <div class="contract">
            <h3>Договор №${contract.contractNumber} (${contract.contractStatus})</h3>
            <p>Банк: ${contract.bank.name}</p>
            <p>Сумма: ${contract.originalAmount} ${contract.currency}</p>
            <p>Ставка: ${contract.interestRate}%</p>
            <p>Период: ${new Date(contract.startDate).toLocaleDateString()} — ${new Date(contract.endDate).toLocaleDateString()}</p>

            <h4>Платежный график</h4>
            <ul>
              ${contract.paymentSchedules.map(sch => `
                <li>${new Date(sch.paymentDate).toLocaleDateString()}: ${sch.paymentAmount} (${sch.status})</li>
              `).join('')}
            </ul>

            <h4>Платежи</h4>
            <ul>
              ${contract.payments.map(p => `
                <li>${new Date(p.paymentDate).toLocaleDateString()}: ${p.amount} (${p.paymentMethod})</li>
              `).join('')}
            </ul>

            <h4>Просрочки</h4>
            <ul>
              ${contract.delinquencies.length > 0 ? contract.delinquencies.map(d => `
                <li>${new Date(d.startDate).toLocaleDateString()} — ${new Date(d.endDate).toLocaleDateString()}: ${d.daysDelinquent} дней</li>
              `).join('') : '<li>Нет</li>'}
            </ul>
          </div>
        `;
        container.innerHTML += contractHtml;
    });
}