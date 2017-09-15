package com.evgenii.jsevaluatortests;

import android.test.AndroidTestCase;

import com.evgenii.jsevaluator.JavaScriptInterface;
import com.evgenii.jsevaluatortests.mocks.CallJavaResultInterfaceMock;

public class JavaScriptInterfaceTest extends AndroidTestCase {
	protected JavaScriptInterface mJavaScriptInterface;
	protected CallJavaResultInterfaceMock mCallJavaResultInterfaceMock;

	public void testReturnResultToJava() {
		mCallJavaResultInterfaceMock = new CallJavaResultInterfaceMock();
		mJavaScriptInterface = new JavaScriptInterface(
				mCallJavaResultInterfaceMock);

		mJavaScriptInterface.returnResultToJava("test value", 12);
		assertEquals("test value",
				mCallJavaResultInterfaceMock.jsCallFinished_paramValue);
		assertEquals(Integer.valueOf(12),
				mCallJavaResultInterfaceMock.jsCallFinished_paramCallIndex);
	}
}