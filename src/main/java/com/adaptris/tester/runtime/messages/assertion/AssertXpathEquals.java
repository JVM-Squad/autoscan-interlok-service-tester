package com.adaptris.tester.runtime.messages.assertion;

import com.adaptris.tester.runtime.ServiceTestException;
import com.adaptris.tester.runtime.XpathCommon;
import com.adaptris.tester.runtime.XpathCommonException;
import com.adaptris.tester.runtime.messages.TestMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("assert-xpath-equals")
public class AssertXpathEquals extends XpathCommon implements Assertion {

  private String uniqueId;
  private String value;

  @Override
  public void setUniqueId(String uniqueId) {
    this.uniqueId = uniqueId;
  }

  @Override
  public String getUniqueId() {
    return uniqueId;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public AssertionResult execute(TestMessage actual) throws ServiceTestException {
    try {
      final String xpathResult = nodeToString(selectSingleNode(actual.getPayload(), getXpath()));
      return new AssertionResult(getUniqueId(), "assert-xpath-equals", getValue().equals(xpathResult));
    } catch (XpathCommonException e) {
      throw new ServiceTestException(e);
    }
  }

  @Override
  public String expected() {
    return "Value [" + getValue() + "] at Xpath [" + getXpath() + "]";
  }

}
