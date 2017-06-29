<?php

namespace App\Classes\Paypal;

class Paypal {
    public static function pay($creditCardInfo, $amount) {
	$account = config('constants.paypal_account');

        $res = 
            "METHOD=doDirectPayment" .
            "&VERSION=" . $account['version'] .
            "&PWD=" . urlencode($account['password']) .
            "&USER=" . urlencode($account['userName']) .
            "&SIGNATURE=" . urlencode($account['signature']) .
            "&PAYMENTACTION=Sale" .
            "&AMT=" . $amount .
            "&CREDITCARDTYPE=" . $creditCardInfo->type .
            "&ACCT=" . $creditCardInfo->number .
            "&EXPDATE=" . $creditCardInfo->month . $creditCardInfo->year .
            "&CVV2=" . $creditCardInfo->cvc .
            "&FIRSTNAME=" . urlencode($creditCardInfo->firstName) .
            "&LASTNAME=" . urlencode($creditCardInfo->lastName) .
            "&STREET=" . urlencode($creditCardInfo->street) .
            "&STREET2=" . urlencode($creditCardInfo->street2) .
            "&CITY=" . urlencode($creditCardInfo->city) .
            "&STATE=" . urlencode($creditCardInfo->state) .
            "&ZIP=" . urlencode($creditCardInfo->zip) .
            "&COUNTRYCODE=" . $creditCardInfo->countryCode .
            "&CURRENCYCODE=USD";
        
        return self::request($res, $account);
    }
    
    public static function refund($transactionId, $refundType, $amount, $note) {
	$account = config('constants.paypal_account');

        $res = 
            "METHOD=RefundTransaction" .
            "&VERSION=" . $account['version'] .
            "&PWD=" . urlencode($account['password']) .
            "&USER=" . urlencode($account['userName']) .
            "&SIGNATURE=" . urlencode($account['signature']) .
            "&TRANSACTIONID=" . urlencode($transactionId) .
            "&REFUNDTYPE=" . urlencode($refundType) .
            "&NOTE=" . urlencode($note) .
            "&CURRENCYCODE=USD";

        if (strtoupper($refundType)=="PARTIAL") { // Full or Partial
            $res = $res .
                "&AMT=" . $amount;
        }

        return self::request($res, $account);
    }

    private static function request($res, $account) {
        $ch = curl_init();
        
        curl_setopt($ch, CURLOPT_URL, $account['endpoint']);
        curl_setopt($ch, CURLOPT_VERBOSE, 1);

        //turning off the server and peer verification(TrustManager Concept).
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, FALSE);
        curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, FALSE);

        curl_setopt($ch, CURLOPT_RETURNTRANSFER,1);
        curl_setopt($ch, CURLOPT_POST, 1);
        
        //setting the res as POST FIELD to curl
        curl_setopt($ch,CURLOPT_POSTFIELDS, $res);

        //getting response from server
        $response = curl_exec($ch);

        if (curl_errno($ch)) {
            // moving to display page to display curl errors
            return [
                "success" => false,
                "request" => $res,
                "errorNo" => curl_errno($ch),
                "errorMessage" => curl_error($ch),
            ];
        } else {
            //closing the curl
            curl_close($ch);

            return [
                "success" => true,
                "request" => $res,
                "response" => $response,
            ];
        }
    }
}
