/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for(int i = 0; i < size(); i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        int i = 0;
        while(i < size() && storage[i].uuid != uuid) {
            i++;
        }
        return storage[i];
    }

    void delete(String uuid) {
        int i = 0;
        while(i < size() && storage[i].uuid != uuid) {
            i++;
        }
        if(storage[i].uuid == uuid) {
            for(int j = i; j < size(); j++) {
                storage[j] = storage[j+1];
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resultSet = new Resume[size()];
        for(int i = 0; i < size(); i++) {
            resultSet[i] = storage[i];
        }
        return resultSet;
    }

    int size() {
        int arrayLength = 0;
        int i = 0;
        while(storage[i] != null) {
            arrayLength++;
            i++;
        }
        return arrayLength;
    }
}
