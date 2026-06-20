package model;

import java.sql.Timestamp;

public class Booking {
    private int id;
    private int userId;
    private int vehicleId;
    private String pickupLocation;
    private String dropoffLocation;
    private Timestamp pickupDatetime;
    private Timestamp dropoffDatetime;
    private int totalDays;
    private double totalCost;
    private String status; // pending, confirmed, completed, cancelled
    private Timestamp bookingDate;
    private String paymentMethod;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }

    public String getDropoffLocation() { return dropoffLocation; }
    public void setDropoffLocation(String dropoffLocation) { this.dropoffLocation = dropoffLocation; }

    public Timestamp getPickupDatetime() { return pickupDatetime; }
    public void setPickupDatetime(Timestamp pickupDatetime) { this.pickupDatetime = pickupDatetime; }

    public Timestamp getDropoffDatetime() { return dropoffDatetime; }
    public void setDropoffDatetime(Timestamp dropoffDatetime) { this.dropoffDatetime = dropoffDatetime; }

    public int getTotalDays() { return totalDays; }
    public void setTotalDays(int totalDays) { this.totalDays = totalDays; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getBookingDate() { return bookingDate; }
    public void setBookingDate(Timestamp bookingDate) { this.bookingDate = bookingDate; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
}