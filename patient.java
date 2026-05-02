
class patient {
    private String firstname;
        private String lastname;
        private String id;
        private String age;
        private char gender;

        public patient(String firstname, String lastname, String id, String age, char gender) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.id = id;
            this.age = age;
            this.gender = gender;
        }



    public String getFirstname() {
            return this.firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return this.lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public  String getAge() {
            return this.age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public char getGender() {
            return this.gender;
        }

        public void setGender(char gender) {
            this.gender = gender;
        }

    public void setConsultationType(String specialist) {
    }
}


