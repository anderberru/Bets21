package businessLogic;

import java.util.Date;
import java.util.List;

import domain.Event;

public class ExtendedIteratorImpl implements ExtendedIterator {
    private int currentPosition;
    private List<Event> eventList; // Event objektuen zerrenda

    public ExtendedIteratorImpl(List<Event> events) {
        this.eventList = events;
        this.currentPosition = 0;
    }

    @Override
    public boolean hasNext() {
        return currentPosition < eventList.size();
    }

    @Override
    public Event next() {
        if (this.hasNext()) {
            return eventList.get(currentPosition++);
        }
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return currentPosition > 0;
    }

    @Override
    public Event previous() {
        if (this.hasPrevious()) {
            return eventList.get(--currentPosition);
        }
        return null;
    }

    @Override
    public void goFirst() {
        currentPosition = 0;
    }

    @Override
    public void goLast() {
        currentPosition = eventList.size() - 1;
    }

	@Override
	public boolean isEmpty() {
		return !hasNext();
	}
}

