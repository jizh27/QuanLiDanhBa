package model;

import java.time.LocalDateTime;

public class Contact {
    private String fullName; 
    private String phoneNumber;
    private String additionalNumbers;  // Các số điện thoại khác
    private String group;
    private LocalDateTime addedTime;
    public Contact(String fullName, String phoneNumber, String additionalNumbers, String group) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.additionalNumbers = additionalNumbers;
        this.group = group;
        this.addedTime = LocalDateTime.now();
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAdditionalNumbers() {
        return additionalNumbers;
    }

    public String getGroup() {
        return group;
    }

    public LocalDateTime getAddedTime() {
        return addedTime;
    }

    @Override
    public String toString() {
        return fullName + " (" + phoneNumber + ")" + 
               (additionalNumbers != null && !additionalNumbers.isEmpty() 
                ? ", thêm: " + additionalNumbers 
                : "") + 
               (group != null && !group.isEmpty() ? " [Nhóm: " + group + "]" : "");
    }
}
