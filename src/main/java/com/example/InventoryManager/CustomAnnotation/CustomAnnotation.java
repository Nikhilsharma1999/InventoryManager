package com.example.InventoryManager.CustomAnnotation;
import java.lang.annotation.*;

@SmartPhone(OS = "ABC", version = 6)
class Phone{
}

public class CustomAnnotation {
    public static void main(String[] args) {
        Phone obj = new Phone();
        Class c = obj.getClass();
        Annotation annotation = c.getAnnotation(SmartPhone.class);
        SmartPhone sP = (SmartPhone) annotation;
        System.out.println(sP.OS());
        System.out.println(sP.version());
    }
}
