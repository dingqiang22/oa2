package com.atguigu.shiroHellow;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyShiro extends AuthorizingRealm{

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {

	UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)token;
	String username = usernamePasswordToken.getUsername();
	System.out.println(username);
	if(username == null){
		throw new UnknownAccountException("用户名不存在");
	}
	String realmName= getName();
	Object credentials="72c896f23f6ed7372fd27e3ca343a3f9";
	String  principal =username;
	ByteSource credentialsSalt =ByteSource.Util.bytes("www.atguigu".getBytes());
	SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt , realmName);
		return info;
	}

public static void main(String[] args) {
int hashIterations=1024;
Object salt="www.atguigu";
Object source="123456";
String algorithmName = "MD5";
SimpleHash simpleHash = new SimpleHash(algorithmName, source, salt, hashIterations);
System.out.println(simpleHash.toString());
}

@Override
protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
	Object primaryPrincipal = arg0.getPrimaryPrincipal();
	Set<String> set =new HashSet<>();
	set.add("user");
	if("admin".equals(primaryPrincipal)){
		set.add("admin");
	}
SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
	info.addRoles(set);
	return info;
}
	

}
