Run AlertMonitorApplication.main()
Change test cases in AlertMonitorApplication.simulateEvents()
Configuration is in config.json file

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
Test 1:
Code :
`public static void simulateEvents() {
    for (int i = 0; i < 15; i++) {
        Client client = Client.X;
        EventType eventType = EventType.PAYMENT_EXCEPTION;

        eventService.addEvent(client, eventType);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}`

Result:
04:12:56.610 [main] INFO com.example.alertmonitor.observers.ConsoleAlertObserver -- Dispatching to Console
04:12:56.612 [main] INFO com.example.alertmonitor.observers.ConsoleAlertObserver -- Client X: PAYMENT_EXCEPTION issue in payment
04:12:56.613 [main] WARN com.example.alertmonitor.observers.ConsoleAlertObserver -- Alert: `issue in payment`
04:12:56.613 [main] INFO com.example.alertmonitor.observers.EmailAlertObserver -- Dispatch an Email
04:12:56.613 [main] INFO com.example.alertmonitor.observers.EmailAlertObserver -- Client X: PAYMENT_EXCEPTION payment exception threshold breached
04:12:56.613 [main] WARN com.example.alertmonitor.observers.EmailAlertObserver -- Alert: `payment exception threshold breached`
Process finished with exit code 0


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
Test 2:
Code:
`public static void simulateEvents() {
    for (int i = 0; i < 12; i++) {
        Client client = Client.X;
        EventType eventType = EventType.USER_SERVICE_EXCEPTION;

        eventService.addEvent(client, eventType);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}`

Result:
04:14:21.111 [main] INFO com.example.alertmonitor.observers.ConsoleAlertObserver -- Dispatching to Console
04:14:21.114 [main] INFO com.example.alertmonitor.observers.ConsoleAlertObserver -- Client X: USER_SERVICE_EXCEPTION issue in user service
04:14:21.117 [main] WARN com.example.alertmonitor.observers.ConsoleAlertObserver -- Alert: `issue in user service`
04:14:21.127 [main] INFO com.example.alertmonitor.observers.ConsoleAlertObserver -- Dispatching to Console
04:14:21.128 [main] INFO com.example.alertmonitor.observers.ConsoleAlertObserver -- Client X: USER_SERVICE_EXCEPTION issue in user service
04:14:21.128 [main] WARN com.example.alertmonitor.observers.ConsoleAlertObserver -- Alert: `issue in user service`
04:14:21.138 [main] INFO com.example.alertmonitor.observers.ConsoleAlertObserver -- Dispatching to Console
04:14:21.138 [main] INFO com.example.alertmonitor.observers.ConsoleAlertObserver -- Client X: USER_SERVICE_EXCEPTION issue in user service
04:14:21.139 [main] WARN com.example.alertmonitor.observers.ConsoleAlertObserver -- Alert: `issue in user service`
Process finished with exit code 0


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
Test 3:
Code:
`public static void simulateEvents() {
    for (int i = 0; i < 8; i++) {
        Client client = Client.X;
        EventType eventType = EventType.USER_SERVICE_EXCEPTION;

        eventService.addEvent(client, eventType);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}`

Result:
Process finished with exit code 0
