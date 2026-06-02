package BTSExercise;

public class ContactBook {

    private BinarySearchTree<Contact> contacts;

    public ContactBook() {
        contacts = new AVL<>();
    }

    public void addContact(String name, String phone, String email) {

        Contact contact = new Contact(name, phone, email);

        contacts.insert(contact);
    }

    public Contact searchContact(String name) {

        Contact searched = new Contact(name, "", "");

        return contacts.search(searched);
    }

    public void deleteContact(String name) {

        Contact contact = new Contact(name, "", "");

        contacts.remove(contact);
    }

    public void displayContacts() {
        contacts.inOrder();
    }

    public void editContact(String name, String newPhone, String newEmail) {

        Contact contact = searchContact(name);

        if (contact != null) {
            contact.setPhone(newPhone);
            contact.setEmail(newEmail);
        }
    }

    public void loadTestData() {

        addContact("Juan", "1111-1111", "juan@gmail.com");
        addContact("Ana", "2222-2222", "ana@gmail.com");
        addContact("Pedro", "3333-3333", "pedro@gmail.com");
        addContact("Lucia", "4444-4444", "lucia@gmail.com");
    }
}