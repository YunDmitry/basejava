/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }

        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        int i = 0;
        while (i < size && storage[i].uuid != uuid) {
            i++;
        }
        if (i == size) {    //дошли до конца массива, резюме не нашли
            return null;
        } else {
            return storage[i];
        }
    }

    void delete(String uuid) {
        int i = 0;
        while (i < size && storage[i].uuid != uuid) {
            i++;
        }
        if (storage[i].uuid == uuid) {
            for (int j = i; j < size; j++) {
                storage[j] = storage[j + 1];
            }
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resultSet = new Resume[size];
        for (int i = 0; i < size; i++) {
            resultSet[i] = storage[i];
        }
        return resultSet;
    }

    int size() {
        return size;
    }
}
