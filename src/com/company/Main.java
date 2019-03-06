package com.company;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String postcode = args[0];
        PostcodeApiHandler paHandler = new PostcodeApiHandler();
        if (!paHandler.validate(postcode)){
            System.out.println("Invalid postcode");
            System.exit(1);
        }
        Postcode details = paHandler.getDetails(postcode);
        System.out.println("Country: " + details.getCountry());
        System.out.println("Region: " + details.getRegion());
        List<String> nearest = paHandler.nearest(postcode);
        System.out.print("Nearest Postcodes: ");
        for (int i = 0; i < nearest.size(); i++) {
            String near = nearest.get(i);
            System.out.print(near);
            if (i < nearest.size()-1) {
                System.out.print("; ");
            }
            else System.out.println();
        }

    }


}
