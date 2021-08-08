package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {

//	サービス実行前にログ出力する
//	対象：[UserService]をクラス名に含む
	@Before("execution(* *..*.*UserService.*(..))")
	public void stertLog(JoinPoint jp) {
		log.info("メソッド開始: " + jp.getSignature());
	}
	
//	サービス実行後にログ出力する
//	対象：[UserService]をクラス名に含む
	@After("execution(* *..*.*UserService.*(..))")
	public void endLog(JoinPoint jp) {
		log.info("メソッド終了: " + jp.getSignature());
	}
	
//	コントローラーの実行前後にログを出力する
	//@Around("bean(*Controller)")
	//@Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
	@Around("@within(org.springframework.stereotype.Controller)")
	public Object startLog(ProceedingJoinPoint jp) throws Throwable {
		log.info("メソッド開始： " + jp.getSignature());
		try {
			//メソッド実行
			Object result = jp.proceed();
			//終了ログを出力
			log.info("メソッド終了： " + jp.getSignature());
			//実行結果をリターン
			return result;
		} catch (Exception e) {
			//エラーログを出力
			log.error("メソッド異常終了： " + jp.getSignature());
			throw e;
		}
	}
}
