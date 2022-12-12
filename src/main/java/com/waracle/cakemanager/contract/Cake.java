package com.waracle.cakemanager.contract;

public record Cake(Long cakeId, String title, String desc, String image, Employee employee) {
    public Cake(Long cakeId, String title, String desc) {
        this(cakeId, title, desc, null, null);
    }

    public Cake(String title, String desc, String image) {
        this(null, title, desc, image, null);
    }
}
