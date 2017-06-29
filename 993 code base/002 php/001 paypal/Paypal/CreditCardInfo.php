<?php

namespace App\Classes\Paypal;

class CreditCardInfo {
    public $firstName;
    public $lastName;
    public $type;
    public $number;
    public $month;
    public $year;
    public $cvc;
    public $street;
    public $street2;
    public $city;
    public $state;
    public $zip;
    public $countryCode;

    public function __construct(
        $firstName,
        $lastName,
        $type,
        $number,
        $month,
        $year,
        $cvc,
        $street,
        $street2,
        $city,
        $state,
        $zip,
        $countryCode)
    {
        $this->firstName = $firstName;
        $this->lastName = $lastName;
        $this->type = $type;
        $this->number = $number;
        $this->month = $month;
        $this->year = $year;
        $this->cvc = $cvc;
        $this->street = $street;
        $this->street2 = $street2;
        $this->city = $city;
        $this->state = $state;
        $this->zip = $zip;
        $this->countryCode = $countryCode;
    }
}
