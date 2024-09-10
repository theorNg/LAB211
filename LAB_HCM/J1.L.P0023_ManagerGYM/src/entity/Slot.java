/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author win
 */
public enum Slot {
    SLOT1("7h30-9h50"),SLOT2("10h-12h20"),SLOT3("12h50-15h10"),SLOT4("15h20-17h40"),
    SLOT5("19h30-21h50");
    private String time;

    private Slot(String time) {
        this.time = time;
    }
    
    public static Slot getSlotByChoice(int choice){
        switch(choice){
            case 1:
                return SLOT1;
            case 2:
                return SLOT2;
            case 3:
                return SLOT3;
            case 4:
                return SLOT4;
            case 5:
                return SLOT5;
        }
        throw new AssertionError();
    }

    public String getTime() {
        return time;
    }
    
}
