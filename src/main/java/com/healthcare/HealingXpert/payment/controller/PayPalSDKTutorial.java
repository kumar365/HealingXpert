//package com.fossgen.healthcare.AidXpert.payment.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.paypal.api.payments.Amount;
//import com.paypal.api.payments.Authorization;
//import com.paypal.api.payments.Details;
//import com.paypal.api.payments.Payer;
//import com.paypal.api.payments.Payment;
//import com.paypal.api.payments.PaymentExecution;
//import com.paypal.api.payments.RedirectUrls;
//import com.paypal.api.payments.Transaction;
//import com.paypal.base.rest.APIContext;
//import com.paypal.base.rest.PayPalRESTException;
//
//public class PayPalSDKTutorial {
//	private static String ID = "AVjG17hGT7yIkBn6mpptrtX5spBBMqhZN8Okc-Qdu9b9DO1-6HOqiUk4kj9AQHB4FPMW-ZibN2GtF00T";
//	private static String Secret = "EB0Q8qRj9RE4Q6Zm1zTXU0O8umeVHCVf9OvwjSJj9-AfGOsl8S1agcGWkxd8uoODc0LS2_hO32L3wmzR";
//	private static String executionMode = "sandbox"; // sandbox or production
//
//	public static void main(String args[]) {
//		PayPalSDKTutorial obj = new PayPalSDKTutorial();
//		obj.capturePayPalAPI();
//	}
//
//	public void capturePayPalAPI() {
//
//		Payer payer = new Payer();
//		payer.setPaymentMethod("paypal");
//		// Redirect URLs
//		RedirectUrls redirectUrls = new RedirectUrls();
//		redirectUrls.setCancelUrl("http://localhost:8080/pay/success");
//		redirectUrls.setReturnUrl("http://localhost:8080/pay/cancle");
//		// Set Payment Details Object
//		Details details = new Details();
//		details.setShipping("2.22");
//		details.setSubtotal("3.33");
//		details.setTax("1.11");
//		// Set Payment amount
//		Amount amount = new Amount();
//		amount.setCurrency("USD");
//		amount.setTotal("6.66");
//		amount.setDetails(details);
//		// Set Transaction information
//		Transaction transaction = new Transaction();
//		transaction.setAmount(amount);
//		transaction.setDescription("Tutorial: How to Invoke PayPal REST API using Java Client?");
//		List<Transaction> transactions = new ArrayList<Transaction>();
//		transactions.add(transaction);
//		// Add Payment details
//		Payment payment = new Payment();
//		// Set Payment intent to authorize
//		payment.setIntent("authorize");
//		payment.setPayer(payer);
//		payment.setTransactions(transactions);
//		payment.setRedirectUrls(redirectUrls);
//		// Pass the clientID, secret and mode. The easiest, and most widely used option.
//		APIContext apiContext = new APIContext(ID, Secret, executionMode);
//		try {
//			Payment myPayment = payment.create(apiContext);
//			System.out.println("createdPayment Obejct Details ==> " + myPayment.toString());
//			// Identifier of the payment resource created
//			payment.setId(myPayment.getId());
//			PaymentExecution paymentExecution = new PaymentExecution();
//			// Set your PayerID. The ID of the Payer, passed in the `return_url` by PayPal.
//			paymentExecution.setPayerId("SQHXBEXMBYGVC");
//			// This call will fail as user has to access Payment on UI. Programmatically
//			// there is no way you can get Payer's consent.
//			Payment createdAuthPayment = payment.execute(apiContext, paymentExecution);
//			// Transactional details including the amount and item details.
//			Authorization crunchifyAuthorization = createdAuthPayment.getTransactions().get(0).getRelatedResources()
//					.get(0).getAuthorization();
//			log("Here is your Authorization ID" + crunchifyAuthorization.getId());
//		} catch (PayPalRESTException e) {
//			// The "standard" error output stream. This stream is already open and ready to
//			// accept output data.
//			System.err.println(e.getDetails());
//		}
//	}
//
//	private void log(String string) {
//		System.out.println(string);
//	}
//}



