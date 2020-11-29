package codewars.fundamentals.four;

// https://www.codewars.com/kata/54acc128329e634e9a000362/

enum Action {
    APP_PASSIVE_OPEN, APP_ACTIVE_OPEN, RCV_SYN, APP_SEND, APP_CLOSE,
    RCV_ACK, RCV_SYN_ACK, RCV_FIN, RCV_FIN_ACK, APP_TIMEOUT,
}

interface TCPHandlerStateI {
    State handleAction(Action action);
}

class ClosedState implements TCPHandlerStateI {
    @Override
    public State handleAction(Action action) {
        switch (action) {
            case APP_PASSIVE_OPEN:
                return State.LISTEN;
            case APP_ACTIVE_OPEN:
                return State.SYN_SENT;
            default:
                return State.ERROR;
        }
    }
}

class ListenState implements TCPHandlerStateI {
    @Override
    public State handleAction(Action action) {
        switch (action) {
            case RCV_SYN:
                return State.SYN_RCVD;
            case APP_SEND:
                return State.SYN_SENT;
            case APP_CLOSE:
                return State.CLOSED;
            default:
                return State.ERROR;
        }
    }
}

class SynReceivedState implements TCPHandlerStateI {
    @Override
    public State handleAction(Action action) {
        switch (action) {
            case APP_CLOSE:
                return State.FIN_WAIT_1;
            case RCV_ACK:
                return State.ESTABLISHED;
            default:
                return State.ERROR;
        }
    }
}

class SynSentState implements TCPHandlerStateI {
    @Override
    public State handleAction(Action action) {
        switch (action) {
            case RCV_SYN:
                return State.SYN_RCVD;
            case RCV_SYN_ACK:
                return State.ESTABLISHED;
            case APP_CLOSE:
                return State.CLOSED;

            default:
                return State.ERROR;
        }
    }
}

class EstablishedState implements TCPHandlerStateI {
    @Override
    public State handleAction(Action action) {
        switch (action) {
            case APP_CLOSE:
                return State.FIN_WAIT_1;
            case RCV_FIN:
                return State.CLOSE_WAIT;
            default:
                return State.ERROR;
        }
    }
}

class FinWait1State implements TCPHandlerStateI {
    @Override
    public State handleAction(Action action) {
        switch (action) {
            case RCV_FIN:
                return State.CLOSING;
            case RCV_FIN_ACK:
                return State.TIME_WAIT;
            case RCV_ACK:
                return State.FIN_WAIT_2;
            default:
                return State.ERROR;
        }
    }
}

class ClosingState implements TCPHandlerStateI {
    @Override
    public State handleAction(Action action) {
        switch (action) {
            case RCV_ACK:
                return State.TIME_WAIT;
            default:
                return State.ERROR;
        }
    }
}

class FinWait2State implements TCPHandlerStateI {
    @Override
    public State handleAction(Action action) {
        switch (action) {
            case RCV_FIN:
                return State.TIME_WAIT;
            default:
                return State.ERROR;
        }
    }
}

class TimeWaitState implements TCPHandlerStateI {
    @Override
    public State handleAction(Action action) {
        switch (action) {
            case APP_TIMEOUT:
                return State.CLOSED;
            default:
                return State.ERROR;
        }
    }
}


class CloseWaitState implements TCPHandlerStateI {
    @Override
    public State handleAction(Action action) {
        switch (action) {
            case APP_CLOSE:
                return State.LAST_ACK;
            default:
                return State.ERROR;
        }
    }
}

class LastAckState implements TCPHandlerStateI {
    @Override
    public State handleAction(Action action) {
        switch (action) {
            case RCV_ACK:
                return State.CLOSED;
            default:
                return State.ERROR;
        }
    }
}

class ErrorState implements TCPHandlerStateI {
    @Override
    public State handleAction(Action action) {
        return State.ERROR;
    }
}

enum State {
    CLOSED(new ClosedState()),
    LISTEN(new ListenState()),
    SYN_RCVD(new SynReceivedState()),
    SYN_SENT(new SynSentState()),
    ESTABLISHED(new EstablishedState()),
    FIN_WAIT_1(new FinWait1State()),
    CLOSING(new ClosingState()),
    FIN_WAIT_2(new FinWait2State()),
    TIME_WAIT(new TimeWaitState()),
    CLOSE_WAIT(new CloseWaitState()),
    LAST_ACK(new LastAckState()),
    ERROR(new ErrorState());

    private final TCPHandlerStateI value;

    public TCPHandlerStateI getValue() {
        return this.value;
    }


    State(TCPHandlerStateI value) {
        this.value = value;
    }
}


class TCPHandler {
    private State state = State.CLOSED;

    void handleAction(Action action) {
        this.state = this.state.getValue().handleAction(action);
    }

    public String getStateName() {
        return state.name();
    }
}

public class TCP {

    public static String traverseStates(String[] events) {
        TCPHandler handler = new TCPHandler();
        for (String e : events) {
            handler.handleAction(Action.valueOf(e));
        }
        return handler.getStateName();
    }
}