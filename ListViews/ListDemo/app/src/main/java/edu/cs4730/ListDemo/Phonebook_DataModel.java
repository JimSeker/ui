package edu.cs4730.ListDemo;

/*
* From http://code.google.com/p/myandroidwidgets/source/browse/trunk/Phonebook/src/com/abeanie/Phonebook.java
* description found at http://techdroid.kbeanie.com/2009/07/custom-listview-for-android.html
*/
public class Phonebook_DataModel {
        private String name;
        private String phone;
        private String mail;
       
        // Constructor for the Phonebook_DataModel class
        public Phonebook_DataModel(String name, String phone, String mail) {
                super();
                this.name = name;
                this.phone = phone;
                this.mail = mail;
        }
       
        // Getter and setter methods for all the fields.
        // Though you would not be using the setters for this example,
        // it might be useful later.
        public String getName() {
                return name;
        }
        public void setName(String name) {
                this.name = name;
        }
        public String getPhone() {
                return phone;
        }
        public void setPhone(String phone) {
                this.phone = phone;
        }
        public String getMail() {
                return mail;
        }
        public void setMail(String mail) {
                this.mail = mail;
        }
}


