<?php

namespace App\Classes\Paypal;

class RefundResult extends PaypalResult {
    public $request;
    public $ack;
    public $version;
    public $build;
    public $correlationId;
    public $errors = "";
    public $refundTransactionId = "";
    public $feeRefundAmount = 0;
    public $grossRefundAmount = 0;
    public $netRefundAmount = 0;
    public $totalRefundedAmount = 0;
    public $currency = "";
    public $created_at;

    public function __construct($request, $nvpstr) {
        $this->request = $request;
        $r = $this->deformat($nvpstr);

        $this->ack = $r['ACK'];
        $this->version = $r['VERSION'];
        $this->build = $r['BUILD'];
        $this->correlationId = $r['CORRELATIONID'];
        $this->created_at = $r['TIMESTAMP'];

        if ($this->ack=="Success") {
            $this->refundtransactionId = $r['REFUNDTRANSACTIONID'];
            $this->feeRefundAmount = $r['FEEREFUNDAMT'];
            $this->grossRefundAmount = $r['GROSSREFUNDAMT'];
            $this->netRefundAmount = $r['NETREFUNDAMT'];
            $this->totalRefundedAmount = $r['TOTALREFUNDEDAMOUNT'];
            $this->currency = $r['CURRENCYCODE'];
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
