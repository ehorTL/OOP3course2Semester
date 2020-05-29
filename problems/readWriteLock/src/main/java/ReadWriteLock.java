public interface ReadWriteLock {
    void lockReader();
    void unlockReader();
    void lockWriter();
    void unlockWriter();
}