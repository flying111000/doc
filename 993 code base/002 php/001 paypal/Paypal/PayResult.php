<?php

namespace App\Classes\Paypal;

class PayResult extends PaypalResult {
    public $request;
    public $ack;
    public $version;
    public $build;
    public $correlationId;
    public $errors = "";
    public $avsCode = "";
    public $cvv2Match = "";
    public $transactionId = "";
    public $amount;
    public $currency;
    public $created_at;

    public function __construct($request, $nvpstr) {
        $this->request = $request;
        $r = $this->deformat($nvpstr);

        $this->ack = $r['ACK'];
        $this->version = $r['VERSION'];
        $this->build = $r['BUILD'];
        $this->correlationId = $r['CORRELATIONID'];
        $this->amount = $r['AMT'];
        $this->currency = $r['CURRENCYCODE'];
        $this->created_at = $r['TIMESTAMP'];

        if ($this->ack=="Success") {
            $this->avsCode = $r['AVSCODE'];
            $this->cvv2Match = $r['CVV2MATCH'];
            $this->transactionId = $r['TRANSACTIONID'];
        }
        else {
            $errors = [];
            
            for($i = 0; ; $i++) {
                if (array_key_exists('L_ERRORCODE' . $i, $r)) {
                    $errors[] = [
                        "errorCode" => $r['L_ERRORCODE' . $i],
                        "shortMessage" => $r['L_SHORTMESSAGE' . $i],
                        "longMessage" => $r['L_LONGMESSAGE' . $i],
                        "severityCode" => $r['L_SEVERITYCODE' . $i],
                    ];
                }
                else {
                    break;
                }
            }

            $this->errors = json_encode($errors);
        }
    }
}
