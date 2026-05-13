package com.ai.productclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    Long stuId;
    String stuName;
    enum stuGender{
        dayi(1,"大一"),
        daer(1,"大一"),
        dasan(1,"大一"),
        dasi(1,"大一");


        private final int gender;
        private final String genderStr;

        stuGender(int gender, String genderStr) {
            this.gender = gender;
            this.genderStr = genderStr;
        }


        public int getGender() {
            return gender;
        }

        public String getGenderStr() {
            return genderStr;
        }

        public static String getGenderStrFromGender(int gender) {
            for(stuGender stugender : stuGender.values()){
                if(stugender.getGender() == gender){
                    return stugender.getGenderStr();
                }
            }
            return null;
        }
    };
    Integer stuAge;
    String stuAddress;
    String stuPhone;
}
