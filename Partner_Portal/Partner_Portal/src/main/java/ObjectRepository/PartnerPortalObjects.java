package ObjectRepository;

import org.openqa.selenium.By;


public class PartnerPortalObjects{
	
    public By ID_TXT= By.xpath("//input[contains(@class,'form-control changeBorderColor inputBoxHeight')]");
    public By PASS_TXT= By.xpath("//input[@placeholder='Enter Login Password']");
    public By LOGIN_BTN= By.xpath("//button[@class='btn submitButton']");
    public By LOGOUT_BTN= By.xpath("//span[@class='infotext']/following-sibling::span/a");
	public By MYPROFILE_LNK=By.xpath("//a[@ng-reflect-router-link='/profile']");
	public By SIGNUP_LNK=By.linkText("Signup");
	public By PASS_DIV=By.xpath("//span[@data-notify='message']");
	public By FORGOTPASS_LNK=By.xpath("//a[contains(@class,'forgot-password')]");
	public By OTP_TXT=By.id("otp1");
	public By PPNAME_TXT=By.xpath("//input[@ng-reflect-name='agentCode']");
    public By SUBMIT_BTN= By.xpath("//button[@class='btn submitButton']");
    public By RESEND_OTP_BTN= By.xpath("//span[@ng-reflect-ng-class='resendOtp");

}
