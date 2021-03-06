package ca.edu.uottawa.csi5380.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

/**
 * Represents an Address of type shipping or billing and
 * it's corresponding information.
 * <p>
 * This class directly maps to the Address table in the
 * database.
 * </p>
 * @author Kenny Byrd
 */
public class Address {

    private long id;
    private String fullName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String province;
    private String country;
    private String zip;
    private String phone;
    private AddressType type;

    public Address() {
        this.id = -1;
        this.fullName = "";
        this.addressLine1 = "";
        this.addressLine2 = "";
        this.city = "";
        this.province = "";
        this.country = "";
        this.zip = "";
        this.phone = "";
        this.type = AddressType.SHIPPING;
    }

    public Address(String fullName, String addressLine1, String addressLine2, String city, String province, String country, String zip, String phone, AddressType type) {
        this.id = -1;
        this.fullName = fullName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.province = province;
        this.country = country;
        this.zip = zip;
        this.phone = phone;
        this.type = type;
    }

    public Address(String fullName, String addressLine1, String addressLine2, String city, String province, String country, String zip, String phone, String type) {
        this.id = -1;
        this.fullName = fullName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.province = province;
        this.country = country;
        this.zip = zip;
        this.phone = phone;
        this.type = AddressType.valueOf(type);
    }

    public Address(long id, String fullName, String addressLine1, String addressLine2, String city, String province,
                   String country, String zip, String phone, String type) {
        this.id = id;
        this.fullName = fullName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.province = province;
        this.country = country;
        this.zip = zip;
        this.phone = phone;
        this.type = StringUtils.isNumeric(type) ? AddressType.values()[Integer.parseInt(type)] : AddressType.valueOf(type);
    }

    public Address(long id, String fullName, String addressLine1, String addressLine2, String city, String province,
                   String country, String zip, String phone, AddressType type) {
        this.id = id;
        this.fullName = fullName;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.province = province;
        this.country = country;
        this.zip = zip;
        this.phone = phone;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressType getType() {
        return type;
    }

    public void setType(AddressType type) {
        this.type = type;
    }

    @JsonIgnore
    public boolean isShippingAddress() {
        return this.type.equals(AddressType.SHIPPING);
    }

    @JsonIgnore
    public boolean isBillingAddress() {
        return this.type.equals(AddressType.BILLING);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (getId() != address.getId()) return false;
        if (!getFullName().equals(address.getFullName())) return false;
        if (!getAddressLine1().equals(address.getAddressLine1())) return false;
        if (!getAddressLine2().equals(address.getAddressLine2())) return false;
        if (!getCity().equals(address.getCity())) return false;
        if (!getProvince().equals(address.getProvince())) return false;
        if (!getCountry().equals(address.getCountry())) return false;
        if (!getZip().equals(address.getZip())) return false;
        if (!getPhone().equals(address.getPhone())) return false;
        return getType() == address.getType();
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getFullName().hashCode();
        result = 31 * result + getAddressLine1().hashCode();
        result = 31 * result + getAddressLine2().hashCode();
        result = 31 * result + getCity().hashCode();
        result = 31 * result + getProvince().hashCode();
        result = 31 * result + getCountry().hashCode();
        result = 31 * result + getZip().hashCode();
        result = 31 * result + getPhone().hashCode();
        result = 31 * result + getType().hashCode();
        return result;
    }
}
