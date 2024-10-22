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

    // Getter cho tên đầy đủ
    public String getFullName() {
        return fullName;
    }

    // Getter cho số điện thoại chính
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Getter cho các số điện thoại khác
    public String getAdditionalNumbers() {
        return additionalNumbers;
    }

    // Getter cho phân nhóm
    public String getGroup() {
        return group;
    }

    // Getter cho thời gian thêm liên hệ
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
