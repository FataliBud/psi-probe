/*
 * Licensed under the GPL License. You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * THIS PACKAGE IS PROVIDED "AS IS" AND WITHOUT ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
 * WITHOUT LIMITATION, THE IMPLIED WARRANTIES OF MERCHANTIBILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE.
 */

package com.googlecode.psiprobe.tools;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.management.AttributeNotFoundException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;

/**
 * The Class JmxTools.
 *
 * @author Vlad Ilyushchenko
 */
public class JmxTools {

  /** The logger. */
  private static final Log logger = LogFactory.getLog(JmxTools.class);

  /**
   * Gets the attribute.
   *
   * @param mbeanServer the mbean server
   * @param objName the obj name
   * @param attrName the attr name
   * @return the attribute
   * @throws Exception the exception
   */
  public static Object getAttribute(MBeanServer mbeanServer, ObjectName objName, String attrName)
      throws Exception {

    try {
      return mbeanServer.getAttribute(objName, attrName);
    } catch (AttributeNotFoundException e) {
      logger.error(objName + " does not have \"" + attrName + "\" attribute");
      return null;
    }
  }

  /**
   * Gets the long attr.
   *
   * @param mbeanServer the mbean server
   * @param objName the obj name
   * @param attrName the attr name
   * @param defaultValue the default value
   * @return the long attr
   */
  public static long getLongAttr(MBeanServer mbeanServer, ObjectName objName, String attrName,
      long defaultValue) {

    try {
      Object obj = mbeanServer.getAttribute(objName, attrName);
      return obj == null ? defaultValue : ((Long) obj);
    } catch (Exception e) {
      return defaultValue;
    }
  }

  /**
   * Gets the long attr.
   *
   * @param cds the cds
   * @param name the name
   * @return the long attr
   */
  public static long getLongAttr(CompositeData cds, String name) {
    Object obj = cds.get(name);
    if (obj != null && obj instanceof Long) {
      return ((Long) obj);
    } else {
      return 0;
    }
  }

  /**
   * Gets the long attr.
   *
   * @param mbeanServer the mbean server
   * @param objName the obj name
   * @param attrName the attr name
   * @return the long attr
   * @throws Exception the exception
   */
  public static long getLongAttr(MBeanServer mbeanServer, ObjectName objName, String attrName)
      throws Exception {

    return ((Long) mbeanServer.getAttribute(objName, attrName));
  }

  /**
   * Gets the int attr.
   *
   * @param mbeanServer the mbean server
   * @param objName the obj name
   * @param attrName the attr name
   * @return the int attr
   * @throws Exception the exception
   */
  public static int getIntAttr(MBeanServer mbeanServer, ObjectName objName, String attrName)
      throws Exception {

    return ((Integer) mbeanServer.getAttribute(objName, attrName));
  }

  /**
   * Gets the int attr.
   *
   * @param cds the cds
   * @param name the name
   * @param defaultValue the default value
   * @return the int attr
   */
  public static int getIntAttr(CompositeData cds, String name, int defaultValue) {
    Object obj = cds.get(name);

    if (obj != null && obj instanceof Integer) {
      return ((Integer) obj);
    } else {
      return defaultValue;
    }
  }

  /**
   * Gets the string attr.
   *
   * @param mbeanServer the mbean server
   * @param objName the obj name
   * @param attrName the attr name
   * @return the string attr
   * @throws Exception the exception
   */
  public static String getStringAttr(MBeanServer mbeanServer, ObjectName objName, String attrName)
      throws Exception {

    Object obj = getAttribute(mbeanServer, objName, attrName);
    return obj == null ? null : obj.toString();
  }

  /**
   * Gets the string attr.
   *
   * @param cds the cds
   * @param name the name
   * @return the string attr
   */
  public static String getStringAttr(CompositeData cds, String name) {
    Object obj = cds.get(name);
    return obj != null ? obj.toString() : null;
  }

  /**
   * Gets the boolean attr.
   *
   * @param cds the cds
   * @param name the name
   * @return the boolean attr
   */
  public static boolean getBooleanAttr(CompositeData cds, String name) {
    Object obj = cds.get(name);
    return obj != null && obj instanceof Boolean && ((Boolean) obj);
  }

  /**
   * Checks for attribute.
   *
   * @param server the server
   * @param mbean the mbean
   * @param attrName the attr name
   * @return true, if successful
   * @throws Exception the exception
   */
  public static boolean hasAttribute(MBeanServer server, ObjectName mbean, String attrName)
      throws Exception {

    MBeanInfo info = server.getMBeanInfo(mbean);
    MBeanAttributeInfo[] ai = info.getAttributes();
    for (MBeanAttributeInfo attribInfo : ai) {
      if (attribInfo.getName().equals(attrName)) {
        return true;
      }
    }
    return false;
  }

}
