package com.team7.cfs.form;

import com.team7.cfs.util.NumberFormatCheck;
import javax.validation.constraints.NotNull;

public class CustomerForm {
        @NotNull
        private String fname;
        @NotNull
        private String lname;
        @NotNull
        private String address;
        @NotNull
        private String city;
        @NotNull
        private String state;
        @NotNull
        private String zip;
        @NotNull
        private String email;
        @NotNull
        private String cash;
        @NotNull
        private String username;
        @NotNull
        private String password;

        //Getter
        public String getFname() {
            return fname;
        }

        public String getLname() {
            return lname;
        }

        public String getAddress() {
            return address;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public String getZip() {
            return zip;
        }

        public String getEmail() {
            return email;
        }

        public String getCash() {
            return cash;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        //Setter
        public void setFname(String fname) {
            this.fname = fname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setState(String state) {
            this.state = state;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setCash(String cash) {
            this.cash = cash;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }
}
