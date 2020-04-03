package zkexample.domain;
 
import java.io.Serializable;
import java.util.Date;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
 
@Entity
@Table(name="userprofile")
public class UserProfile implements Serializable {
 
   /**
  * 
  */
 private static final long serialVersionUID = 1L;
 
    @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
   private long id;
     
    private String firstName;
   private String lastName;
    private String middleName;
  private String userAccountNumber;
   private String SSN;
 private String address1;
    private String address2;
    private String city;
    private String State;
   private String zipCode;
 private String email;
   private String userLoginID;
 private String password;
     
    @Temporal(TemporalType.DATE)
    private Date DOB;
   public long getId() {
       return id;
  }
 
   public void setId(long id) {
        this.id = id;
   }
 
   public String getFirstName() {
      return firstName;
   }
 
   public void setFirstName(String firstName) {
        this.firstName = firstName;
 }
 
   public String getLastName() {
       return lastName;
    }
 
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }
 
   public String getMiddleName() {
     return middleName;
  }
 
   public void setMiddleName(String middleName) {
      this.middleName = middleName;
   }
 
   public String getUserAccountNumber() {
      return userAccountNumber;
   }
 
   public void setUserAccountNumber(String userAccountNumber) {
        this.userAccountNumber = userAccountNumber;
 }
 
     
   public Date getDOB() {
      return DOB;
 }
 
   public void setDOB(Date dOB) {
      DOB = dOB;
  }
 
   public String getSSN() {
        return SSN;
 }
 
   public void setSSN(String sSN) {
        SSN = sSN;
  }
 
   public String getAddress1() {
       return address1;
    }
 
   public void setAddress1(String address1) {
      this.address1 = address1;
   }
 
   public String getAddress2() {
       return address2;
    }
 
   public void setAddress2(String address2) {
      this.address2 = address2;
   }
 
   public String getCity() {
       return city;
    }
 
   public void setCity(String city) {
      this.city = city;
   }
 
   public String getState() {
      return State;
   }
 
   public void setState(String state) {
        State = state;
  }
 
   public String getZipCode() {
        return zipCode;
 }
 
   public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
 }
 
   public String getEmail() {
      return email;
   }
 
   public void setEmail(String email) {
        this.email = email;
 }
 
   public String getUserLoginID() {
        return userLoginID;
 }
 
   public void setUserLoginID(String userLoginID) {
        this.userLoginID = userLoginID;
 }
 
   public String getPassword() {
       return password;
    }
 
   public void setPassword(String password) {
      this.password = password;
   }
 
}