Програма вирішує завдання відповідно до варіанту номер 1. Було реалізовано підхід Work stealing з використанням Fork/Join Framework, де фактично завдання поділяються на підзадачі, які виконуються асинхронно. Код проходить по файлам певної директорії та знаходить серед них файли певного формату (наприклад, усі файли з розширенням .pdf). У результаті виводиться кількість знайдених файлів у заданій користувачем директорії.
Було обрано підхід Work stealing, оскільки він швидше виконує завдання та більш зрозумілий у реалізації.
