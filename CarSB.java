/* 
* Name:        Siobhan Boylan
*
* Course:      CS-12, Fall 2022
*
* Date:        12/5/22
* 
* Filename:    CarSB.java
* 
* Purpose:     Create a class from scratch
*/

// new class: CarSB
public class CarSB {

    // ----------------------
    //   Instance Variables
    // ----------------------
    private String make;
    private String model;
    private int year;
    private double odometer;
    private double tankLevel;
    private double tankSize;
    private CS12Date saleDate;
    private double salePrice;

    
    // -------------
    //   Constants  
    // -------------
    private final double TOL = 0.0001;  // equals FP tolerance
    
    // --------------------
    //   Other Class Data    
    // --------------------
    
    // for use with getMPG(), drive() and fuel()
    private double tripMiles;
    private double tripGallons;
    private double tripMPG;
    
    // format specifiers
    private static String fmtList = "%s  %s  %d  %,.1f  %.3f  %.1f  %s  %,.2f";
    private static String fmtStr = "%-40s%s\n";
    private static String fmtInt = "%-40s%d\n";
    private static String fmtDbl = "%-40s%,.1f\n";
    private static String fmtPrice = "%-40s%,.2f\n";
    private static String fmtTkLvl = "%-40s%.3f\n";
    
    
    // --------------------------------------------------------------------------------
    
    
    // ----------------
    //   CONSTRUCTORS
    // ----------------
    
    // default constructor method
    public CarSB() {
        make = "Sierra";
        model = "Wolverine";
        year = UtilsSB.today().getYear();
        odometer = 0.0;
        tankLevel = 0.0;
        tankSize = 0.0;
        saleDate = new CS12Date();
        salePrice = 0.0;
        tripMiles = 0.0;
        tripGallons = 0.0;
        tripMPG = 25.0;
        
    }
    
    
    // full constructor method
    public CarSB(String make, 
                 String model, 
                 int year, 
                 double odometer, 
                 double tankLevel, 
                 double tankSize, 
                 CS12Date saleDate, 
                 double salePrice) {
        
        // pull in defaults for all fields
        this();
        
        // update instance variables based on user inputs
        setMake(make);
        setModel(model);
        setYear(year);
        setOdometer(odometer);
        setTankSize(tankSize);
        setTankLevel(tankLevel);
        setSaleDate(saleDate);
        setSalePrice(salePrice);
    }
    
    
    // Alt 1 constructor: make/model/year
    public CarSB(String make, String model, int year) {
        
        // pull in defaults for all fields
        this();
        
        // update instance variables based on user inputs
        setMake(make);
        setModel(model);
        setYear(year);
    }
    
    
    // Alt 2 constructor: make/model/odometer
    public CarSB(String make, String model, double odometer) {
        
        // pull in defaults for all fields
        this();
        
        // update instance variables based on user inputs
        setMake(make);
        setModel(model);
        setOdometer(odometer);
    }
    
    
    // --------------------------------------------------------------------------------
    
    
    // -------------------
    //   DISPLAY METHODS
    // -------------------
    
    // toString()
    
    // displays 8 instance variables in API order using SPACE-separated format
    public String toString() {
        return String.format(fmtList, 
                             make, 
                             model, 
                             year, 
                             odometer, 
                             tankLevel, 
                             tankSize, 
                             saleDate, 
                             salePrice
                             );
    }
    
    
    // print()
    // displays all 8 instance variables in API order, in a multi-line labeled format
    // displays UNITS as shown for numerical miles, gallons, and cost
    public void print() {
        System.out.printf(fmtStr, "make:", getMake());
        System.out.printf(fmtStr, "model:", getModel());
        System.out.printf(fmtInt, "model year:", getYear());
        System.out.printf(fmtDbl, "odometer [miles]:", getOdometer());
        System.out.printf(fmtTkLvl, "tank level [gals]:", getTankLevel());
        System.out.printf(fmtDbl, "tank size [gals]:", getTankSize());
        System.out.printf(fmtStr, "last sale date:", getSaleDate());
        System.out.printf(fmtPrice, "last sale price [$]: ", getSalePrice());
        UtilsSB.spacer('-', 50);
        System.out.printf(fmtInt, "age [years]: ", getAge());
        System.out.printf(fmtStr, "status: ", getStatus());
        System.out.printf(fmtPrice, "depreciated value [$]: ", getValue(5));
        System.out.printf(fmtDbl, "remaining fuel [%]: ", getFuelPct());
        System.out.printf(fmtDbl, "full travel range [miles]: ", getFullRange());
        System.out.printf(fmtDbl, "remaining travel range [miles]: ", getTripRange());
        System.out.printf(fmtDbl, "current MPG [miles/gal]: ", getMPG());
        UtilsSB.spacer('-', 50);
        System.out.printf(fmtDbl, "travel since last fill-up [miles]: ", this.tripMiles);
        System.out.printf(fmtTkLvl, "fuel used since last fill-up [gals]: ", this.tripGallons);
        UtilsSB.blank();
        
    }
    
    
    // OVERLOADED print()
    // prepends a bordered text message header to the basic print()
    public void print(String msg) {
        UtilsSB.message(msg, true);
        print();
    }
    
    // display()
    // displays all instance variables and some certain data 
    //   in a prescribed, labeled, "ad-like" 3-line format
    public void display() {
        // 3-line 'online ad'
        // status, model year, make & model
        System.out.printf("FOR SALE: %s %d %s %s \n", getStatus(), getYear(), getMake(), getModel());
        
        // tank size, MPG, and mileage
        // if tripMPG has been reset due to recent refueling,
        // display default MPG, 25.0
        if (this.tripMPG == 0) {
            System.out.printf("%.1f-gal tank %.1f MPG %,.1f miles\n", getTankSize(), 25.0, getOdometer());
        }
        else {
            System.out.printf("%.1f%s %.2f %s %,.1f %s\n", getTankSize(), "-gal tank,", getMPG(), "MPG,", getOdometer(), "miles");
        }
        
        // last sale date & asking price
        //    >> depreciated value (using 8-year)
        System.out.printf("last sold %s $%,.2f asking price $%,.2f\n", getSaleDate(), getSalePrice(), getValue(8));
        
    }
    
    
    // --------------------------------------------------------------------------------
    
    
    // ---------------------------
    // ACCESSOR /  MUTATOR METHODS
    // ---------------------------
    
    // @@@@@@
    //  MAKE
    // @@@@@@
    
    // GET make
    public String getMake() {
        return this.make;
    }
    
    // SET make
    public void setMake(String make) {
        this.make = make;
    }
    
    // SET make (user-input)
    public void setMake(boolean guiMode) {
        String aMake = UtilsSB.readString("Enter car's MAKE: ", guiMode);
        setMake(aMake);
    }
    
    // @@@@@@@
    //  MODEL
    // @@@@@@@
    
    // GET model
    public String getModel() {
        return this.model;
    }
    
    // SET model
    public void setModel(String model) {
        this.model = model;
    }
    
    // SET model (user-input)
    public void setModel(boolean guiMode) {
        String aModel = UtilsSB.readString("Enter car's MODEL: ", guiMode);
        setModel(aModel);
    }
    
    // @@@@@@
    //  YEAR
    // @@@@@@
    
    // GET year
    public int getYear() {
        return this.year;
    }
    
    // SET year
    public void setYear(int year) {
        // data check:
        
        // historically accurate/possible
        if (year < 1900) {
            UtilsSB.message(
                            String.format(
                                        "ERROR: Year must be >= 1900, (invalid input: %d)",
                                        year
                                    ), 
                                '#'
                            );
        }
        
        // set valid value
        else {
            this.year = year;
        }
    }
    
    // SET year (user-input)
    public void setYear(boolean guiMode) {
        int aYear = UtilsSB.readInt("Enter car's Model YEAR: ", guiMode);
        
        // data check:
        
        // historically accurate/possible
        while (aYear < 1900) {
            UtilsSB.message(
                            String.format(
                                        "ERROR: Year must be >= 1900, (invalid input: %d)",
                                        year
                                    ), 
                                '#'
                            );
            aYear = UtilsSB.readInt("\nEnter valid year: ", guiMode);
        }
        
        // set valid value
        setYear(aYear);
    }
    
    // @@@@@@@@@@
    //  ODOMETER
    // @@@@@@@@@@
    
    // GET odometer
    public double getOdometer() {
        return this.odometer;
    }
    
    // SET odometer
    public void setOdometer(double odometer) {
        
        // data checks:
        
        // can't be negative
        if (odometer < 0.0) {
            UtilsSB.message(
                            String.format(
                                        "ERROR: Odometer must be >= 0.0, (invalid input: %d)",
                                        odometer
                                    ), 
                                '#'
                            );
        }
        
        // can't LOWER odometer
        else if (odometer < this.odometer) {
            UtilsSB.message(
                            String.format(
                                        "ILLEGAL: Cannot roll back odometer! (invalid input: %d)",
                                        odometer
                                    ), 
                                '#'
                            );
        }
        
        // set valid value
        else {
            this.odometer = odometer;
        }
    }
    
    // SET odometer (user-input)
    public void setOdometer(boolean guiMode) {
        double anOdometer = UtilsSB.readDouble("Enter car's ODOMETER (miles): ", guiMode);
        
        // prompt for valid inputs
        
        // odometer > 0
        while (anOdometer <= 0.0) {
            UtilsSB.message(
                            String.format(
                                        "ERROR: Odometer must be >= 0.0, (invalid input: %d)",
                                        odometer
                                    ), 
                                '#'
                            );
            odometer = UtilsSB.readDouble(
                            "\nEnter valid odometer: ", 
                            guiMode);
        }
        
        // new odometer > previous odometer
        while (anOdometer < this.odometer) {
            UtilsSB.message(
                            String.format(
                                        "ILLEGAL: Cannot roll back odometer! (invalid input: %d)",
                                        odometer
                                    ), 
                                '#'
                            );
            odometer = UtilsSB.readDouble(
                            "\nEnter valid odometer: ", 
                            guiMode);
        }
        
        // set valid values
        setOdometer(anOdometer);
    }
    
    // @@@@@@@@@@@@
    //  TANK LEVEL
    // @@@@@@@@@@@@
    
    // GET gas tank level
    public double getTankLevel() {
        return this.tankLevel;
    }
    
    // SET gas tank level
    public void setTankLevel(double tankLevel) {
        // data checks:
        
        // tankLevel can't be negative
        if (tankLevel < 0.0) {
            UtilsSB.message(
                            String.format(
                                        "ERROR: Fuel tank level must be >= 0 (invalid input: %d)",
                                        tankLevel
                                    ), 
                                '#'
                            );
        }
        
        // tank LEVEL must be <= tank SIZE
        else if (tankLevel > getTankSize()) {
            UtilsSB.message(
                            String.format(
                                        "ERROR: Tank level (%.3F) exceeds tank size (%.1f)",
                                        tankLevel,
                                        getTankSize()
                                    ), 
                                '#'
                            );
        }
        
        // set valid value
        else {
            this.tankLevel = tankLevel;
        }
    }
    
    // SET gas tank level (user-input)
    public void setTankLevel(boolean guiMode) {
        double aTankLevel = UtilsSB.readDouble("Enter car's TANK LEVEL (#gal): ", guiMode);
        
        // data checks:
        
        // tank level can't be negative
        while (aTankLevel < 0.0) {
            UtilsSB.message(
                            String.format(
                                        "ERROR: Fuel tank level must be >= 0 (invalid input: %d)",
                                        tankLevel
                                    ), 
                                '#'
                            );
            aTankLevel = UtilsSB.readDouble(
                                "\nEnter valid tank level: ", 
                                guiMode);
        
        }
        
        while (aTankLevel > getTankSize()) {
            UtilsSB.message(
                            String.format(
                                        "ERROR: Tank level (%.3F) exceeds tank size (%.1f)",
                                        tankLevel,
                                        getTankSize()
                                    ), 
                                '#'
                            );
            aTankLevel = UtilsSB.readDouble(
                                "\nEnter valid tank level: ", 
                                guiMode);
        }
        
        // set valid value
        setTankLevel(aTankLevel);
    }
    
    
    // @@@@@@@@@@@
    //  TANK SIZE
    // @@@@@@@@@@@
    
    
    // GET gas tank size
    public double getTankSize() {
        return this.tankSize;
    }
    
    // SET gas tank size
    public void setTankSize(double tankSize) {
        // data checks:
        
        // tankSize can't be negative
        if (tankSize < 0.0) {
            UtilsSB.message(
                            String.format(
                                        "ERROR: Fuel tank size must be >= 0 (invalid input: %d)",
                                        tankSize
                                    ), 
                                '#'
                            );
        }
        
        // tank LEVEL must be <= tank SIZE
        else if (getTankLevel() > tankSize) {
            UtilsSB.spacer('#', 30);
            UtilsSB.message("ERROR: New tank capacity, " + tankSize + 
                            " gallons,\n is smaller than current fuel level of " + 
                            getTankLevel() + " gallons!", false);
            UtilsSB.spacer('#', 30);
        }
        
        // set valid value
        else {
            this.tankSize = tankSize;
        }
        
    }
    
    // SET gas tank size (user-input)
    public void setTankSize(boolean guiMode) {
        double aTankSize = UtilsSB.readDouble("Enter car's TANK SIZE (#gal): ", guiMode);
        
        // data check:
        
        // tankSize can't be negative
        while (tankSize < 0.0) {
            UtilsSB.message(
                            String.format(
                                        "ERROR: Fuel tank size must be >= 0 (invalid input: %d)",
                                        tankSize
                                    ), 
                                '#'
                            );
            aTankSize = UtilsSB.readDouble(
                                "\nEnter valid tank size: ", 
                                guiMode);
        
        }
        
        while (getTankLevel() > tankSize) {
            UtilsSB.spacer('#', 30);
            UtilsSB.message("ERROR: New tank capacity, " + tankSize + 
                            " gallons,\n is smaller than current fuel level of " + 
                            getTankLevel() + " gallons!", false);
            UtilsSB.spacer('#', 30);
            aTankSize = UtilsSB.readDouble(
                                "\nEnter valid tank size: ", 
                                guiMode);
        }
        
        // set valid value
        setTankSize(aTankSize);
    }
    
    // @@@@@@@@@@@
    //  SALE DATE
    // @@@@@@@@@@@
    
    // GET most recent sale DATE
    public CS12Date getSaleDate() {
        CS12Date saleDateCopy = new CS12Date(
                                            this.saleDate.getMonth(),
                                            this.saleDate.getDay(),
                                            this.saleDate.getYear()
                                            );
        return saleDateCopy;
    }
    
    // SET most recent sale DATE
    public void setSaleDate(CS12Date saleDate) {
        // no data check needed here - CS12Date class has built-in checks
        this.saleDate = new CS12Date(
                                    saleDate.getMonth(), 
                                    saleDate.getDay(), 
                                    saleDate.getYear()
                                    );
    }
    
    // SET most recent sale DATE (user-input)
    public void setSaleDate(boolean guiMode) {
        
        // set up while-loop in case user makes error 
        // that prevents update due to 'no change' in CS12Date's built-in error checks 
        boolean retry = true;
        
        while (retry == true) {
        
            // create intermediate date instance
            // to make use of included error checking in CS12Date class
            // set to current sale date, which won't change
            // if user inputs invalid day/month/year
            CS12Date aSaleDate = getSaleDate();
            
            // use CS12Date's mutators to update 'aSaleDate'
            
            // get & set month 
            aSaleDate.setMonth(UtilsSB.readInt("Enter MONTH of sale date: ", guiMode));
            
            // get & set day
            aSaleDate.setDay(UtilsSB.readInt("Enter DAY of sale date: ", guiMode));
            
            // get & set year 
            aSaleDate.setYear(UtilsSB.readInt("Enter YEAR of sale date: ", guiMode));
            
            // check if user is satisfied with new date input
            char msg = UtilsSB.readChar(String.format(
                                            "New sale date: %s -- is this correct? (y/n) ", 
                                            aSaleDate.toString()), guiMode); 
            // if user is satisfied (Y/y)
            if (msg == 'Y' || msg == 'y'){
                
                // store new date info to Car's saleDate
                setSaleDate(aSaleDate);
                
                // end while loop
                retry = false;
                break;
            }
            
            // no else needed:
            // sale date will remain unchanged
            
        }
    }
    
    // @@@@@@@@@@@@
    //  SALE PRICE
    // @@@@@@@@@@@@
    
    // GET most recent sale PRICE
    public double getSalePrice() {
        return this.salePrice;
    }
    
    // SET most recent sale PRICE
    public void setSalePrice(double salePrice) {
        // data check:
        
        // price must be greater than 0
        if (salePrice < 0) {
            UtilsSB.message(
                            String.format(
                                        "ERROR: Sale Price must be >= $0 (invalid input: %.2f)",
                                        salePrice
                                    ), 
                                '$'
                            );
        }
        
        // set valid value
        else {
            this.salePrice = salePrice;
        }
    }
    
    // SET most recent sale PRICE (user-input)
    public void setSalePrice(boolean guiMode) {
         double aSalePrice= UtilsSB.readDouble("Enter SALE PRICE ($): ", guiMode);
         
         // data check:
         while (aSalePrice < 0){
            UtilsSB.message(
                            String.format(
                                        "ERROR: Sale Price must be >= $0 (invalid input: %.2f)",
                                        salePrice
                                    ), 
                                '$'
                            );
            aSalePrice= UtilsSB.readDouble("\nEnter valid sale price: ", guiMode);
         }
         
         // set valid value
         setSalePrice(aSalePrice);
    }
    
    // >>>>>>>>>>>>>>>>>>>>>>>>
    // end accessors / mutators
    // <<<<<<<<<<<<<<<<<<<<<<<<
    
    
    // --------------------------------------------------------------------------------
    
    
    // -------------------
    //   EQUALITY METHOD
    // -------------------
    
    // Overrides the default equals() method to compare instances of CarSB objects
    public boolean equals(Object obj) {
        boolean status;
        // first, check whether objects are the same type
        if (!(obj instanceof CarSB)) {
            // can stop bc not same object type
            status = false;
        }
        
        else {
            // typecast into the intended object types
            CarSB c = (CarSB) obj;
            
            // check field-by-field on ALL fields
            if ( (c.getMake().equals(getMake())) &&
                 (c.getModel().equals(getModel())) &&
                 (Math.abs(c.getYear()-getYear()) <= TOL) &&
                 (Math.abs(c.getOdometer()-getOdometer()) <= TOL) &&
                 (Math.abs(c.getTankLevel()-getTankLevel()) <= TOL) &&
                 (Math.abs(c.getTankSize()-getTankSize()) <= TOL) &&
                 (c.getSaleDate().equals(getSaleDate())) &&
                 (Math.abs(c.getSalePrice()-getSalePrice()) <= TOL) ) {
                 
                status = true;
            }
            else {
                status = false;
            }
        } 
        
        // after all checks, return status (true/false)
        return status;
        
    } // end equals
    
    
    // --------------------------------------------------------------------------------
    
    
    // -----------------------------
    // DERIVED DATA ACCESSOR METHODS
    // -----------------------------
    
    // -------
    // GET AGE
    // -------
    // as of today
    int getAge(){
        // use 'getAge()' from UtilsSB
        // 
        return UtilsSB.getAge(new CS12Date(1, 1, getYear()));
    }
    
    // ----------
    // GET STATUS 
    // ----------
    // (new/used) based on age
    // if age == 0, status = 'new'
    // if age > 0, status = 'used'
    String getStatus(){
        int carAge = getAge();
        if (carAge < 0){
            return "prototype";
        }
        else if (carAge == 0) {
            return "new";
        }
        else {
            return "used";
        }
    }
    
    // ---------
    // GET VALUE
    // ---------  
    // based on last sale price & current age
    // using depreciation schedule provided
    double getValue(int deprYears){
        double deprCost;
        // data checks:
        
        // (SILENT) if prototype, no depreciation
        if (getAge() <= 0) {
            return getSalePrice();
        }
        
        // depreciation years must be either 5 or 8
        else if (deprYears != 5 && deprYears != 8) {
            UtilsSB.message("ERROR: depreciation schedule must be 5 or 8 years ONLY!", '*');
            return getSalePrice();
        }
        
        else {
            deprCost = getSalePrice() - (getAge() * (getSalePrice() / deprYears));
            
            // (SILENT) depreciated value can't go below 0.0
            if (deprCost < 0) {
                return 0.0;
            }
            else {
                return deprCost;
            }
        }
    }
    
    // ----------------
    // GET FUEL PERCENT
    // ----------------
    // as percent of full tank capacity
    double getFuelPct(){
        // use current tankLevel divided by tankSize
        
        // data check (SILENT):
        
        // guard against divide-by-zero
        if (getTankSize() <= 0){
            return 0.0;
        }
        else {
            return (getTankLevel() / getTankSize()) * 100;
        }
    }
    
    // --------------
    // GET FULL RANGE
    // --------------
    // assuming full tank
    // assuming current MPG
    double getFullRange(){
        // full range = MPG * tank size
        return this.tripMPG * getTankSize();
    }
    
    // --------------
    // GET TRIP RANGE
    // --------------
    // get remaining travel range 
    // at current fuel level
    // assuming current MPG
    double getTripRange() {
        // TRIP range = MPG * tank level
        return this.tripMPG * getTankLevel();
    }
    
    // -------
    // GET MPG
    // -------
    // get current miles per gallon
    // since most recent fueling
    double getMPG() {
        // using "hidden" instance variables
        // account for zero values if NOT default constructor
        
        // data check (SILENT):
        // if the car HAS been driven, calculate & update new tripMPG
        if (this.tripGallons > 0) {
            this.tripMPG = this.tripMiles / this.tripGallons;
        }
        
        // if the car hasn't been driven, no calculation needed:
        // return the previously stored tripMPG (might be 0)
        
        return this.tripMPG;
    }
    
    // >>>>>>>>>>>>>>>>>>>>>>>>
    // end data accessors
    // <<<<<<<<<<<<<<<<<<<<<<<<
    
    // --------------------------------------------------------------------------------
    
    
    // ---------------
    // UTILITY METHODS
    // ---------------
    
    
    // ********
    //  UPDATE
    // ********
    // update ALL instance variables at once
    // using prompted user input
    public void update(boolean mode){
        // TODO implement method
        setMake(mode);
        setModel(mode);
        setYear(mode);
        setOdometer(mode);
        setTankSize(mode);
        setTankLevel(mode);
        setSaleDate(mode);
        setSalePrice(mode);
    }
    
    // ******************
    //  DRIVE-overloaded
    // ******************
    // drive specified distance
    // at specified MPG
    void drive(double miles, double mpg){
        
        // data checks:
        
        // input miles > 0
        if (miles <= 0) {
            UtilsSB.message("ERROR: Miles driven must be > 0!", '#');
        }
        
        // input mpg > 0
        else if (mpg <= 0) {
            UtilsSB.message("ERROR: Mileage must be > 0!", '#');
        }
        
        // check Car has enough fuel
        // miles / mileage = fuel needed to drive x miles
        else if (getTankLevel() < miles / mpg) {
            UtilsSB.message(
                            String.format(
                                            "ERROR: At specified mileage (%.1f mpg), fuel level (%.1f gal) is insufficient for a(n) %.1f mile drive!.", 
                                            mpg,
                                            getTankLevel(), 
                                            miles
                                        ), 
                                '#'
                            );
            UtilsSB.blank();
        }
        
        // update valid data
        else {
            // add 'miles' to odometer
            this.odometer += miles;
            
            // add to tripMiles
            this.tripMiles += miles;
            
            // update tripGallons (gals used since last refuel)
            this.tripGallons += miles / mpg;
            
            // subtract gallons from tank
            this.tankLevel -= miles / mpg;
            
            // update MPG
            this.tripMPG = this.tripMiles / this.tripGallons;
            
        }
    }
    
    // *************
    //  DRIVE-basic
    // *************
    // drive specified distance
    // assuming current MPG
    void drive(double miles){
        
        // check Car has enough fuel
        if (getTankLevel() < miles / this.tripMPG) {
            UtilsSB.message(
                            String.format(
                                            "ERROR: At specified mileage (%.1f mpg), fuel level (%.1f gal) is insufficient for a(n) %.1f mile drive!.", 
                                            this.tripMPG,
                                            getTankLevel(), 
                                            miles
                                        ), 
                                '#'
                            );
            UtilsSB.blank();
        }
        
        else {
            this.drive(miles, getMPG());
        }
    }
    
    // ******
    //  FUEL
    // ******
    // add specified amount of fuel
    void fuel(double gallons){
    
        // data checks:
        
        // input gallons must be > 0
        if (gallons <= 0) {
            UtilsSB.message(
                            String.format(
                                            "ERROR: Must add more than %.3f gallons!",
                                            gallons
                                         ), 
                                '>'
                            );
        }
        
        // check if room in tank for added fuel
        else if (getTankLevel() + gallons > getTankSize()) {
            UtilsSB.message(
                            String.format(
                                            "ERROR: adding %.3f gallons to current tank level (%.3f) will overflow tank's %.3f-gallon capacity!", 
                                            gallons, 
                                            getTankLevel(),
                                            getTankSize()
                                         ), 
                                '>'
                            );
        }
        
        else {
        
            // add gallons to tank
            this.tankLevel += gallons;
            
            // reset tripGallons (fuel used for trip)
            this.tripGallons = 0;
            
            // reset tripMiles (miles traveled for trip)
            this.tripMiles = 0;
            
        }
    }
    
    // ******
    //  SELL
    // ******
    // sell Car for specified price
    // on a given date
    // sale price == 0 indicates donation
    void sell(double price, CS12Date date){
        
        // data check:
        
        // sale price cannot be less than 0
        if (price < 0) {
            UtilsSB.message("ERROR: Sale price cannot be negative!", '$');
        }
        
        // set salePrice 
        else {
            setSalePrice(price);
        }
        
        // set saleDate (can backdate)
        setSaleDate(date);
    }
    
    // >>>>>>>>>>>>>>>>>>>
    // end utility methods
    // <<<<<<<<<<<<<<<<<<<
    
    // .....................
    //   end class methods
    // .....................
    
    // --------------------------------------------------------------------------------
    
    
    // main method:
    public static void main (String [] args) {
    
        // Tests:
        
        // // --------------------------------------------------
//         //test 1 - create car objects
//         UtilsSB.message("test 1: create 4 Car objects, using all 4 constructor forms", true);
//         
         // default constructor
         CarSB defaultCar = new CarSB(); 
         
         // full constructor   
         CarSB fullCar = new CarSB("Toyota", "Prius", 2009, 130000, 5.0, 12, new CS12Date(10, 2, 2017), 10800);
         
         // constructor w/ make, model, year
         CarSB alt1Car = new CarSB("Suburu", "CrossTrek", 2222);
//         
//         // constructor w make, model, odometer
//         CarSB alt2Car = new CarSB("Ford", "Maverick", 1000.0);
//         
//         System.out.println("(no visible output)");
//         
//         UtilsSB.blank(2);
//         
//         UtilsSB.pause();
//         
//         // --------------------------------------------------
//         //test 2 - display created objects using toString()
//          UtilsSB.message("test 2a - display created objects using toString() & full print", true);
//          UtilsSB.blank();
//          
//          // default car toString()
//          System.out.println(defaultCar.toString());
//          UtilsSB.blank();
//          
//          // default car print()
//          defaultCar.print("print default constructor car");
//          UtilsSB.blank(2);
// 
//          // full car toString()
//          System.out.println(fullCar.toString());
//          UtilsSB.blank();
//         
//         // full car print()
//         fullCar.print("print full constructor car");
//         UtilsSB.blank(2);
//         
//         UtilsSB.pause();
//         
//         // --------------------------------------------------
//         UtilsSB.message("test 2b - display created objects using toString() & no-message print", true);
//         UtilsSB.blank();
//         
//         // alt 1 car toString()
//         System.out.println(alt1Car.toString());
//         UtilsSB.blank();
//         
//         alt1Car.print();
//         UtilsSB.blank(2);
//         
//         // alt 2 car toString()
//         System.out.println(alt2Car.toString());
//         UtilsSB.blank();
//         
//         alt2Car.print();
//         UtilsSB.blank(2);
//         
//         UtilsSB.pause();
//         
//         // --------------------------------------------------
//         // test 3: test all accessors - print ONE object, and extract each field alone using gets
//         UtilsSB.message("test 3: test all accessors - print ONE object, and extract each field alone using gets", true);
//         UtilsSB.blank(2);
//         
//         defaultCar.print("Starting car");
//         
//         UtilsSB.blank(2);
//         
//         UtilsSB.message("all instance vars displayed using each get...", false);
//         System.out.printf(fmtStr, "make alone:", defaultCar.getMake());
//         System.out.printf(fmtStr, "model alone:", defaultCar.getModel());
//         System.out.printf(fmtInt, "year alone:", defaultCar.getYear());
//         System.out.printf(fmtDbl, "odometer alone:", defaultCar.getOdometer());
//         System.out.printf(fmtDbl, "tank level alone:", defaultCar.getTankLevel());
//         System.out.printf(fmtDbl, "tank size alone:", defaultCar.getTankSize());
//         System.out.printf(fmtStr, "sale date alone:", defaultCar.getSaleDate());
//         System.out.printf(fmtPrice, "sale price alone:", defaultCar.getSalePrice());
//         
//         UtilsSB.blank(2);
//         
//         UtilsSB.pause();
//         
//         // --------------------------------------------------
//         // test 4: test all mutators - print default object before/after updating each field        
//         UtilsSB.message("test 4: test all mutators - print default object before/after updating each field", true);
//         UtilsSB.blank(2);
//         
//         defaultCar.print("default car as starting point");
//         UtilsSB.blank();
//         
//         UtilsSB.message("all instance vars individually updated using sets (made-up data)", false);
//         UtilsSB.blank();
//         
//         defaultCar.setMake("Henson");
//         defaultCar.setModel("MuppetMobile");
//         defaultCar.setYear(1972);
//         defaultCar.setOdometer(300000);
//         defaultCar.setTankLevel(3);
//         defaultCar.setTankSize(35);
//         defaultCar.setSaleDate(new CS12Date(1,1,1980));
//         defaultCar.setSalePrice(1000000);
//         
//         defaultCar.print("default car updated");
//         UtilsSB.blank();
//         
//         UtilsSB.pause();
//         
//         // --------------------------------------------------
//         // test 5: test all prompting mutators - print default object before/after updating each field        
//         UtilsSB.message("test 5: test all prompting mutators - print default object before/after updating each field", true);
//         UtilsSB.blank();
//         
//         defaultCar.print("starting default car");
//         UtilsSB.blank(2);
//         
//         UtilsSB.message("update each field - note this can be absolute garbage data for now!", false);
//         
//         defaultCar.setMake(false);
//         defaultCar.setModel(false);
//         defaultCar.setYear(false);
//         defaultCar.setOdometer(false);
//         defaultCar.setTankLevel(false);
//         defaultCar.setTankSize(false);
//         defaultCar.setSaleDate(false);
//         defaultCar.setSalePrice(false);
//         
//         UtilsSB.blank();
//         
//         defaultCar.print("after all updates");
//         UtilsSB.blank(2);
//         
//         UtilsSB.pause();
//         
//         // --------------------------------------------------
//         // test 6: test equals - same objs, different objs, and different object types        
//         UtilsSB.message("test 6: test equals - same objs, different objs, and different object types", true);
//         
//         CarSB car1 = new CarSB();
//         CarSB car2 = new CarSB("Mini", "Cooper", 1999);
//         
//         System.out.printf("%-30s%s\n", "test car1 vs car1:", car1.equals(car1));
//         System.out.printf("%-30s%s\n", "test car1 vs car2:", car1.equals(car2));
//         System.out.printf("%-30s%s\n", "test car1 vs any String:", car1.equals("hello"));
//         System.out.printf("%-30s%s\n", "test car1 vs any CS12Date:", car1.equals(new CS12Date()));
//         
//         // --------------------------------------------------
//         // End of Week 1 testing        
//         UtilsSB.message("End of Week 1 testing", true);
//         UtilsSB.blank(); 

//          System.out.print("fullCar's tank SIZE = " + fullCar.getTankSize() + "\n");
//          System.out.print("fullCar's end tank level = " + fullCar.getTankLevel() + "\n");
//          System.out.print("fullCar's end odometer = " + fullCar.getOdometer() + "\n");
//          System.out.print("fullCar's end tripGallons =" + fullCar.tripGallons + "\n");
//          System.out.print("fullCar's end tripMiles =" + fullCar.tripMiles + "\n");
//          System.out.print("fullCar's end tripMPG =" + fullCar.tripMPG + "\n");
//         // fullCar.sell(3000, new CS12Date(2,2,1922));
         // fullCar.print(); 
         // fullCar.fuel(80);
         // System.out.print("fullCar's tank level after driving 75mi= " + fullCar.getTankLevel() + "\n");
//          UtilsSB.message("add fuel", '>');
//          fullCar.fuel(10);
//          System.out.print("fullCar's tank level after refuel 10 gal = " + fullCar.getTankLevel() + "\n");
//          UtilsSB.message("drive car", '>');
//          fullCar.drive(175, 40);
//          System.out.print("fullCar's tank level after driving 175mi = " + fullCar.getTankLevel() + "\n");
//          UtilsSB.message("new print", '*');
//          fullCar.print();
         // System.out.print("fullCar value = " + fullCar.getValue(5));       
         
//         CarSB test = new CarSB();
//         CS12Date tempDate, sellDate;
//         int numDays = 3;
//         int year = UtilsSB.today().getYear();
//         CS12Date MEAS_DATE = new CS12Date(11, 1, year);
//         
//         test.print("default constructor");
//         test = new CarSB("Sierra", "Wolverine", year, 5000.0, 15.0, 30.0, MEAS_DATE, 40000.00);
//         tempDate = test.getSaleDate();
//         System.out.println("test saleDate = " + tempDate);

        int [] array1 = {1, 2, 3, 4, 5};
        int [] array2 = {10, 20, 30, 40, 50};
        int [] result = {0, 0, 0, 0, 0};
        
        // int res;
//         int a1;
//         int a2;
//         
//         res = result[(result.length / 2) - 1];
//         a1 = array1[0];
//         a2 = array2[array2.length - 1];
        
        result[(result.length / 2) - 1] = array1[0] + array2[array2.length - 1];
        System.out.printf("result[middle] (%d) = array1[0] (%d) + array2[last] (%d)", result[(result.length /2) - 1], array1[0], array2[array2.length - 1]);

    }       
        
}