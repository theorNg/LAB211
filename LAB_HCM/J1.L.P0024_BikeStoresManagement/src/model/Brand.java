/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author win
 */
public class Brand {
    private String brandId;
    private String brandName;
    private String origin;

    public Brand(String brandId, String brandName, String origin) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.origin = origin;
    }

    public Brand() {
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "Brand{"  + brandId + ", " + brandName + ", " + origin + '}';
    }
    
}
