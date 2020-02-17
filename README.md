This project has been done by using technologies java, spring boot, rest webservice and sqLite as database.

The JUnit tests have been written only for controller and validation where there is some logic to test, and rest of beans and service classes test have not been written.
For Data Base code there are no tests written, since that is an embedded database having crud operations.

CreateOffer for a product has been implemented by assuming offer information is available in embedded database.
Exception handling, JUnit tests have been implemented in this project with OOP concepts.

URL pattern to Test:

GetOffer:
1) http://localhost:8080/shopping/offer/1  -- Result : Success
2) http://localhost:8080/shopping/offer/3  -- Result : Failure - Offer has been expired.

CreateOffer(Post):
1) http://localhost:8080/shopping/offer  -- Result : Success, and returns ProductOffer object with offerId
Body :
{
    "termsAndConditions": null,
    "vat": 0,
    "discountPercentage": 0,
    "createDate": "2020-02-15T19:58:54.141+0000",
    "prodId": 1,
    "name": "DELL laptop",
    "price": 400,
    "currency": null,
    "description": "DELL Inspiron",
    "shippingAddress": null,
    "image": null,
    "discountedPrice": 0
}
