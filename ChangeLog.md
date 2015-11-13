# Change log #
<a href='Hidden comment: This content is generated. Do not modify!'></a>
<table border='1' cellspacing='0'><thead><tr><th>Date</th><th>Action</th><th>Category</th><th>Incompatible?</th><th>Description</th></tr></thead><tbody>
<tr border='1'><td>10/12/13</td><td><i>Release <b>3.12.0</b></i></td></tr>
<tr><td>10/12/13</td><td>add</td><td>api</td><td></td><td>Added all available jQuery method permutations as Java code :)</td></tr>
<tr><td>10/12/13</td><td>update</td><td>api</td><td></td><td>Added all available jQuery methods at least without parameter mapping</td></tr>
<tr><td>10/11/13</td><td>change</td><td>api</td><td><b>yes</b></td><td>Renamed AbstractJQueryInvocation method 'eq' to '<code>_</code>eq' to not interfere with 'eq' from AbstractJSExpression</td></tr>
<tr><td>10/11/13</td><td>add</td><td>api</td><td></td><td>Extended AbstractJQueryInvocation with new overloads</td></tr>
<tr><td>10/11/13</td><td>change</td><td>api</td><td></td><td>Added support for HTML5 data attributes in IHCElement</td></tr>
<tr border='1'><td>10/10/13</td><td><i>Release <b>3.11.1</b></i></td></tr>
<tr><td>10/10/13</td><td>change</td><td>api</td><td></td><td>IHCTable implements IHCHasChildren again</td></tr>
<tr border='1'><td>10/9/13</td><td><i>Release <b>3.11.0</b></i></td></tr>
<tr><td>10/9/13</td><td>change</td><td>api</td><td></td><td>Renamed IHCBaseTable to IHCTable</td></tr>
<tr><td>10/9/13</td><td>change</td><td>api</td><td></td><td>IHCBaseTable now extends IHCElement</td></tr>
<tr border='1'><td>10/2/13</td><td><i>Release <b>3.10.3</b></i></td></tr>
<tr><td>10/2/13</td><td>add</td><td>api</td><td></td><td>Added IHCButton methods setOnClick(ISimpleURL) and addOnClick(ISimpleURL)</td></tr>
<tr><td>10/2/13</td><td>add</td><td>api</td><td></td><td>Added support for IJson from <a href='http://code.google.com/p/phloc-json'>phloc-json</a> 2.4.0</td></tr>
<tr border='1'><td>9/23/13</td><td><i>Release <b>3.10.2</b></i></td></tr>
<tr border='1'><td>9/23/13</td><td><i>Release <b>3.10.1</b></i></td></tr>
<tr border='1'><td>9/13/13</td><td><i>Release <b>3.10.0</b></i></td></tr>
<tr><td>9/12/13</td><td>change</td><td>api</td><td><b>yes</b></td><td>Changed JQuery API to extract properties</td></tr>
<tr><td>9/4/13</td><td>remove</td><td>api</td><td><b>yes</b></td><td>Removed deprecated methods</td></tr>
<tr><td>9/2/13</td><td>add</td><td>api</td><td></td><td>Extracted interface for common base classes</td></tr>
<tr border='1'><td>8/30/13</td><td><i>Release <b>3.9.4</b></i></td></tr>
<tr><td>8/30/13</td><td>add</td><td>api</td><td></td><td>Added new method JQuery methods</td></tr>
<tr><td>7/18/13</td><td>add</td><td>api</td><td></td><td>Added new method AbstractHCSpecialNodes.clear()</td></tr>
<tr border='1'><td>6/24/13</td><td><i>Release <b>3.9.3</b></i></td></tr>
<tr><td>6/24/13</td><td>update</td><td>api</td><td></td><td>Made HCSpecialNodeHandler more flexible so that it can be used with any AbstractHCSpecialNodes instances</td></tr>
<tr border='1'><td>6/18/13</td><td><i>Release <b>3.9.2</b></i></td></tr>
<tr><td>6/18/13</td><td>add</td><td>api</td><td></td><td>Added getAllChildrenAsNodeList () to IHCNodeWithChildren</td></tr>
<tr><td>6/18/13</td><td>add</td><td>api</td><td></td><td>Added prependEventHandler(EJSEvent,IJSCodeProvider) to IHCElement</td></tr>
<tr><td>6/18/13</td><td>add</td><td>api</td><td></td><td>Added implementation methods from AbstractHCElement into IHCElement</td></tr>
<tr><td>6/18/13</td><td>add</td><td>api</td><td></td><td>Added method JSEventMap.prepend</td></tr>
<tr><td>6/18/13</td><td>add</td><td>api</td><td></td><td>Extended CollectingJSCodeProvider with prepend(Flattened) and addAtIndex(Flattened)</td></tr>
<tr border='1'><td>5/31/13</td><td><i>Release <b>3.9.1</b></i></td></tr>
<tr><td>5/31/13</td><td>add</td><td>data</td><td></td><td>Added all ARIA attribute names to CHTMLAttributes</td></tr>
<tr><td>5/31/13</td><td>add</td><td>api</td><td></td><td>Added support for the HTML role attribute</td></tr>
<tr border='1'><td>4/22/13</td><td><i>Release <b>3.9.0</b></i></td></tr>
<tr><td>4/18/13</td><td>add</td><td>api</td><td></td><td>Added possibility in HCCheckbox to disable the creation of hidden fields</td></tr>
<tr><td>4/17/13</td><td>update</td><td>tech</td><td></td><td>Updated to <a href='http://code.google.com/p/phloc-commons'>phloc-commons</a> 4.0.3</td></tr>
<tr><td>4/5/13</td><td>add</td><td>api</td><td></td><td>CollectingJSCodeProvider now implements ICloneable</td></tr>
<tr><td>4/5/13</td><td>add</td><td>api</td><td></td><td>Added new class HCSpecialNodeHandler for JS/CSS node handling</td></tr>
<tr><td>4/4/13</td><td>add</td><td>api</td><td></td><td>Added new method HCSetting.getAsHTMLStringWithoutNamespaces</td></tr>
<tr><td>3/28/13</td><td>add</td><td>api</td><td></td><td>Added new HCRadioButton constructor overloads</td></tr>
<tr><td>3/20/13</td><td>add</td><td>api</td><td></td><td>Extended JSHtml method list</td></tr>
<tr border='1'><td>2/24/13</td><td><i>Release <b>3.8.5</b></i></td></tr>
<tr><td>2/20/13</td><td>change</td><td>api</td><td></td><td>Extracted base interface IHCHasCSSClasses</td></tr>
<tr><td>2/20/13</td><td>change</td><td>api</td><td><b>yes</b></td><td>Interface IHCHasCSSStyles is no longer derived from IHCNode</td></tr>
<tr><td>2/19/13</td><td>change</td><td>tech</td><td><b>yes</b></td><td>Using the new interfaces IHasElementName and IHasAttributeValue from <a href='http://code.google.com/p/phloc-commons'>phloc-commons</a> 3.9.6</td></tr>
<tr><td>2/19/13</td><td>add</td><td>api</td><td></td><td>IMetaElement can now convert itself into an IMicroNode</td></tr>
<tr><td>2/14/13</td><td>add</td><td>api</td><td></td><td>Extended ICHElement with getAllClassesAsString() and getAllStylesAsString()</td></tr>
<tr><td>1/30/13</td><td>add</td><td>api</td><td></td><td>Added new abstract base class AbstractHCNodeList for more type-safe HCNodeList inheritance</td></tr>
<tr border='1'><td>1/16/13</td><td><i>Release <b>3.8.0</b></i></td></tr>
<tr><td>1/14/13</td><td>add</td><td>api</td><td></td><td>Extended APIs to support IHCNodeBuilder more easily</td></tr>
<tr><td>1/7/13</td><td>add</td><td>api</td><td></td><td>Added missing custom attribute access methods in IHCElement</td></tr>
<tr><td>1/7/13</td><td>change</td><td>api</td><td><b>yes</b></td><td>Merged IHCBaseNode and IHCNode into IHCNode and deleted IHCBaseNode</td></tr>
<tr border='1'><td>1/6/13</td><td><i>Release <b>3.7.3</b></i></td></tr>
<tr><td>1/5/13</td><td>change</td><td>api</td><td><b>yes</b></td><td>Moved <code>_</code>return methods from JSBlock to AbstractJSBlock</td></tr>
<tr><td>1/3/13</td><td>add</td><td>api</td><td></td><td>Added support for 'accept' attribute in HCEditFile</td></tr>
<tr><td>1/3/13</td><td>add</td><td>api</td><td></td><td>Added new class HCMultiCustomizer that can wrap several customizers</td></tr>
<tr><td>12/28/12</td><td>add</td><td>api</td><td><b>yes</b></td><td>Added support for prefix increment and decrement</td></tr>
<tr><td>12/28/12</td><td>add</td><td>api</td><td><b>yes</b></td><td>Moved all methods from AbstractJSExpression to IJSExpression</td></tr>
<tr border='1'><td>12/28/12</td><td><i>Release <b>3.7.2</b></i></td></tr>
<tr><td>12/28/12</td><td>add</td><td>api</td><td></td><td>Extended JQuerySelectorList API</td></tr>
<tr><td>12/27/12</td><td>add</td><td>api</td><td><b>yes</b></td><td>Added some methods to IJSExpression and IJSAssignmentTarget</td></tr>
<tr><td>12/27/12</td><td>change</td><td>api</td><td><b>yes</b></td><td>Added a common base class AbstractJSBlock to JSBlock and JSPackage</td></tr>
<tr><td>12/27/12</td><td>fix</td><td>tech</td><td></td><td>Fixed generated code of JSDOM for-in-loop</td></tr>
<tr><td>12/27/12</td><td>fix</td><td>tech</td><td></td><td>Fixed generated code of JSDOM break and continue with labels</td></tr>
<tr><td>12/27/12</td><td>add</td><td>api</td><td><b>yes</b></td><td>Added new class JSRef being the base class of JSFieldRef handling global references</td></tr>
<tr><td>12/27/12</td><td>add</td><td>api</td><td></td><td>Finished adding equals/hashCode to all JSDOM classes</td></tr>
<tr><td>12/26/12</td><td>add</td><td>api</td><td></td><td>Extended API of IHCConversionSettings</td></tr>
<tr><td>12/23/12</td><td>add</td><td>api</td><td></td><td>Started implementing some equals/hashCode for certain IJSExpression implementations</td></tr>
<tr><td>12/23/12</td><td>add</td><td>api</td><td></td><td>Extended API of JSArray and JSAssocArray</td></tr>
<tr><td>12/16/12</td><td>add</td><td>api</td><td></td><td>Added support for multiple in HCEditFile</td></tr>
<tr border='1'><td>12/14/12</td><td><i>Release <b>3.7.1</b></i></td></tr>
<tr><td>11/26/12</td><td>change</td><td>api</td><td></td><td>ConstantJSPathProvider and ConstantCSSPathProvider now implement equals and hashCode</td></tr>
<tr><td>11/23/12</td><td>add</td><td>api</td><td></td><td>Added new class JSGlobal with global JS functions like parseFloat, escape, decodeURI etc.</td></tr>
<tr border='1'><td>10/8/12</td><td><i>Release <b>3.7.0</b></i></td></tr>
<tr><td>10/4/12</td><td>change</td><td>tech</td><td><b>yes</b></td><td>Using the correct HTML namespace URIs for element creation</td></tr>
<tr><td>10/1/12</td><td>add</td><td>api</td><td></td><td>Improved available HTML5 element set</td></tr>
<tr><td>10/1/12</td><td>change</td><td>api</td><td><b>yes</b></td><td>Changed class hierarchy - IHCNode is only a stub for IHCBaseNode</td></tr>
<tr><td>9/24/12</td><td>change</td><td>api</td><td><b>yes</b></td><td>Reworked out-of-band-node handling</td></tr>
<tr><td>9/25/12</td><td>add</td><td>api</td><td></td><td>Added new base interface IHCNodeBuilder for the builder pattern</td></tr>
<tr><td>9/20/12</td><td>update</td><td>performance</td><td></td><td>Performance improvement by using <a href='http://code.google.com/p/phloc-commons'>phloc-commons</a> 3.8.4</td></tr>
<tr border='1'><td>9/18/12</td><td><i>Release <b>3.6.1</b></i></td></tr>
<tr><td>9/10/12</td><td>add</td><td>api</td><td></td><td>Extended HCUtils with list2brList and list2divList methods</td></tr>
<tr><td>9/10/12</td><td>add</td><td>api</td><td></td><td>Added new HCLabel factory methods</td></tr>
<tr><td>9/10/12</td><td>add</td><td>api</td><td></td><td>Added new method IHCNodeWithChildren.addChildren(IPredefinedLocaleTextProvider<a href='.md'>.md</a>)</td></tr>
<tr border='1'><td>9/10/12</td><td><i>Release <b>3.6.0</b></i></td></tr>
<tr><td>9/10/12</td><td>add</td><td>api</td><td></td><td>Added some more factory methods for easier and more consistent usage</td></tr>
<tr border='1'><td>9/10/12</td><td><i>Release <b>3.6.0.RC2</b></i></td></tr>
<tr><td>9/10/12</td><td>add</td><td>api</td><td></td><td>Backward compatibility fix for HCLabel</td></tr>
<tr><td>9/10/12</td><td>add</td><td>api</td><td></td><td>Added automatically created HCNoScript and HCNoEmbed</td></tr>
<tr border='1'><td>9/10/12</td><td><i>Release <b>3.6.0.RC1</b></i></td></tr>
<tr><td>9/9/12</td><td>change</td><td>api</td><td><b>yes</b></td><td>Extended AbstractHBaseTable API and made all members private</td></tr>
<tr><td>9/9/12</td><td>change</td><td>api</td><td><b>yes</b></td><td>Removed or deprecated constructors that lead to "Constructor Calls Overridable Method"</td></tr>
<tr><td>9/8/12</td><td>fix</td><td>api</td><td></td><td>Fixed using instanceof in JSDOM - leading and trailing blanks were missing</td></tr>
<tr border='1'><td>9/6/12</td><td><i>Release <b>3.5.0</b></i></td></tr>
<tr><td>9/6/12</td><td>update</td><td>tech</td><td></td><td>Updated to <a href='http://code.google.com/p/phloc-commons'>phloc-commons</a> 3.8.0</td></tr>
<tr border='1'><td>9/5/12</td><td><i>Release <b>3.5.0.RC1</b></i></td></tr>
<tr><td>9/5/12</td><td>add</td><td>api</td><td></td><td>Added default custom implementation HCConversionSettingsProvider for different HTML versions</td></tr>
<tr><td>9/5/12</td><td>change</td><td>api</td><td><b>yes</b></td><td>Deprecated old conditional comment handling as well as IHTMLResourceObject and everything derived from it</td></tr>
<tr><td>9/5/12</td><td>add</td><td>api</td><td></td><td>Added new IHCNode implementation that can handle conditional comments (HCConditionalCommentNode)</td></tr>
<tr><td>9/5/12</td><td>change</td><td>api</td><td><b>yes</b></td><td>Changed the out-of-band-handler interface so that the HCHead element is added as a parameter</td></tr>
<tr><td>9/5/12</td><td>add</td><td>api</td><td></td><td>Extended HCHead and made the out-of-band-node handler mandatory</td></tr>
<tr><td>9/5/12</td><td>add</td><td>api</td><td></td><td>Extracted interface IHCConvertible</td></tr>
<tr><td>9/5/12</td><td>add</td><td>api</td><td></td><td>HCScriptFile has now support for the async and charset attributes</td></tr>
<tr><td>9/5/12</td><td>fix</td><td>tech</td><td></td><td>Fixed a problem that null class values are emitted</td></tr>
<tr border='1'><td>9/4/12</td><td><i>Release <b>3.4.3</b></i></td></tr>
<tr><td>9/4/12</td><td>add</td><td>api</td><td></td><td>Made all HC<code>*</code> impl classes non-final</td></tr>
<tr border='1'><td>9/4/12</td><td><i>Release <b>3.4.2</b></i></td></tr>
<tr><td>9/4/12</td><td>add</td><td>api</td><td></td><td>Updated table API so that things can be removed as well</td></tr>
<tr border='1'><td>9/4/12</td><td><i>Release <b>3.4.1</b></i></td></tr>
<tr><td>9/3/12</td><td>fix</td><td>api</td><td></td><td>Fixed return type of AbstractHCElementWithInternalChildren.getFirstChild and getLastChild</td></tr>
<tr><td>9/3/12</td><td>add</td><td>api</td><td></td><td>Made HCButton<code>_</code>Submit and HCButton<code>_</code>Reset non-final</td></tr>
<tr><td>8/31/12</td><td>add</td><td>api</td><td></td><td>Table rows now allow to add cells at a specified index</td></tr>
<tr><td>8/31/12</td><td>add</td><td>api</td><td></td><td>Table and colgroup now allow to add columns at a specified index</td></tr>
<tr border='1'><td>8/29/12</td><td><i>Release <b>3.4.0</b></i></td></tr>
<tr><td>8/29/12</td><td>change</td><td>api</td><td></td><td>HCStyle is no longer an AbstractHCElementWithChildren but only an AbstractHCElement</td></tr>
<tr><td>8/29/12</td><td>change</td><td>api</td><td></td><td>Added configuration capabilities on how to emit HCStyle and HCScript</td></tr>
<tr border='1'><td>8/29/12</td><td><i>Release <b>3.4.0.RC4</b></i></td></tr>
<tr><td>8/29/12</td><td>add</td><td>api</td><td></td><td>AbstractHCElementWithChildren got a new protected callback method beforeAddChild</td></tr>
<tr><td>8/29/12</td><td>remove</td><td>api</td><td></td><td>Removed all deprecated elements</td></tr>
<tr border='1'><td>8/27/12</td><td><i>Release <b>3.4.0.RC3</b></i></td></tr>
<tr border='1'><td>8/22/12</td><td><i>Release <b>3.4.0.RC2</b></i></td></tr>
<tr><td>8/22/12</td><td>change</td><td>api</td><td></td><td>Some changes in JS builder</td></tr>
<tr border='1'><td>8/22/12</td><td><i>Release <b>3.4.0.RC1</b></i></td></tr>
<tr><td>8/17/12</td><td>add</td><td>api</td><td></td><td>Added new package js.builder with a DSL to generate structured JS code on the fly. Based on the com.sun.codemodel 2.6 library</td></tr>
<tr><td>8/12/12</td><td>add</td><td>api</td><td></td><td>AbstractHCElement now supports HTML5 attribute dropzone</td></tr>
<tr border='1'><td>8/5/12</td><td><i>Release <b>3.3.5</b></i></td></tr>
<tr><td>8/5/12</td><td>add</td><td>api</td><td></td><td>Improved accessor methods in several HC implementation classes</td></tr>
<tr><td>8/5/12</td><td>add</td><td>api</td><td></td><td>Added new protected method AbstractHCElement.prepareOnceBeforeCreateElement that is called exactly once during preparation</td></tr>
<tr><td>8/5/12</td><td>add</td><td>api</td><td></td><td>Improved CSS style and CSS class handling API in IHCElement</td></tr>
<tr><td>8/5/12</td><td>add</td><td>api</td><td></td><td>DefaultCSSClassProvider implements equals/hashCode</td></tr>
<tr border='1'><td>8/4/12</td><td><i>Release <b>3.3.4</b></i></td></tr>
<tr><td>8/4/12</td><td>remove</td><td>api</td><td></td><td>Added some more HC<code>*</code> methods</td></tr>
<tr><td>8/4/12</td><td>update</td><td>tech</td><td></td><td>Updated to <a href='http://code.google.com/p/phloc-css'>phloc-css</a> 3.1.1</td></tr>
<tr border='1'><td>8/3/12</td><td><i>Release <b>3.3.3</b></i></td></tr>
<tr><td>8/3/12</td><td>remove</td><td>api</td><td></td><td>Removed 3 JSAction<code>*</code> classes as they were very unmotivated :)</td></tr>
<tr><td>7/20/12</td><td>add</td><td>api</td><td></td><td>Added new constructors to CSSExternal and CSSInternal</td></tr>
<tr border='1'><td>7/20/12</td><td><i>Release <b>3.3.2</b></i></td></tr>
<tr><td>7/20/12</td><td>add</td><td>api</td><td></td><td>Added new interfaces I(CSS|JS)PathProviderWithDependencies</td></tr>
<tr><td>7/20/12</td><td>add</td><td>api</td><td></td><td>Made CSSExternal, CSSInline and JSInline non-final</td></tr>
<tr><td>7/19/12</td><td>add</td><td>api</td><td></td><td>Added additional constructors for CSSExternal and CSSInline</td></tr>
<tr><td>7/19/12</td><td>add</td><td>api</td><td></td><td>Added new interfaces IJSPathProviderWithParam and ICSSPathProviderWithParam</td></tr>
<tr><td>7/19/12</td><td>update</td><td>api</td><td></td><td>Extended CollectingJSCodeProvider to handle IJSON objects</td></tr>
<tr border='1'><td>7/13/12</td><td><i>Release <b>3.3.1</b></i></td></tr>
<tr><td>7/13/12</td><td>update</td><td>tech</td><td></td><td>Updated to <a href='http://code.google.com/p/phloc-json'>phloc-json</a> 2.2.8</td></tr>
<tr><td>7/9/12</td><td>add</td><td>api</td><td></td><td>Added new method JSCodeWrapper.getVariableAssignment with an optional boolean to emit the 'var ' keyword or not</td></tr>
<tr><td>6/28/12</td><td>update</td><td>tech</td><td></td><td>Updated to <a href='http://code.google.com/p/phloc-css'>phloc-css</a> 3.1.0</td></tr>
<tr border='1'><td>6/18/12</td><td><i>Release <b>3.3.0</b></i></td></tr>
<tr><td>6/18/12</td><td>update</td><td>tech</td><td></td><td>Updated to <a href='http://code.google.com/p/phloc-css'>phloc-css</a> 3.0.0</td></tr>
<tr border='1'><td>6/18/12</td><td><i>Release <b>3.3.0.RC2</b></i></td></tr>
<tr><td>6/18/12</td><td>update</td><td>tech</td><td><b>yes</b></td><td>Updated to <a href='http://code.google.com/p/phloc-css'>phloc-css</a> 3.0.0-RC3</td></tr>
<tr border='1'><td>6/15/12</td><td><i>Release <b>3.3.0.RC1</b></i></td></tr>
<tr><td>6/15/12</td><td>update</td><td>tech</td><td><b>yes</b></td><td>Updated to <a href='http://code.google.com/p/phloc-css'>phloc-css</a> 3.0.0-RC2</td></tr>
<tr><td>6/6/12</td><td>update</td><td>api</td><td></td><td>Extended IHCConversionSettings with getCSSVersion to determine the CSS version to use</td></tr>
<tr><td>6/6/12</td><td>add</td><td>api</td><td></td><td>Added new method AbstractHCBaseTable.getBodyRowAtIndex</td></tr>
<tr border='1'><td>6/4/12</td><td><i>Release <b>3.2.0</b></i></td></tr>
<tr><td>6/4/12</td><td>change</td><td>api</td><td><b>yes</b></td><td>Changed the IHCCustomizer interface to also take the EHTMLVersion required for customization</td></tr>
<tr><td>6/4/12</td><td>change</td><td>api</td><td></td><td>Marked CSSInlineHelper as deprecated because it only works on the first URL and does not parse the CSS correctly</td></tr>
<tr><td>6/4/12</td><td>add</td><td>api</td><td></td><td>Add HC<code>*</code> classes so that they match the form "HC" + "HTML ElementName" and deprecated the old classes</td></tr>
<tr><td>6/4/12</td><td>add</td><td>api</td><td><b>yes</b></td><td>Extracted IHCConversionSettings interface from HCConversionSettings</td></tr>
<tr><td>6/4/12</td><td>add</td><td>api</td><td></td><td>Added more getter methods in HC<code>*</code> classes</td></tr>
<tr><td>6/4/12</td><td>add</td><td>api</td><td></td><td>Added special HTML5 IHCConversionSettingsProvider</td></tr>
<tr><td>6/4/12</td><td>fix</td><td>tech</td><td></td><td>Not emitting XML namespace in HTML5 documents</td></tr>
<tr><td>6/4/12</td><td>fix</td><td>tech</td><td></td><td>Fixed HTML element name in HCTrack</td></tr>
<tr><td>6/1/12</td><td>update</td><td>tech</td><td></td><td>Made static self-closed check in EHTMLElement case-insensitive and added positive check method</td></tr>
<tr border='1'><td>5/24/12</td><td><i>Release <b>3.1.6</b></i></td></tr>
<tr><td>5/24/12</td><td>update</td><td>tech</td><td></td><td>Updated to <a href='http://code.google.com/p/phloc-css'>phloc-css</a> 2.7.0</td></tr>
<tr border='1'><td>5/22/12</td><td><i>Release <b>3.1.5</b></i></td></tr>
<tr><td>5/22/12</td><td>add</td><td>api</td><td></td><td>Extended API of HCDefinitionDefinition, HCDefinitionTerm and HCLI</td></tr>
<tr><td>5/22/12</td><td>fix</td><td>tech</td><td></td><td>Fixed self-closing tag handling for tables</td></tr>
<tr><td>5/22/12</td><td>fix</td><td>tech</td><td></td><td>Fixed typos in rarely used HTML element names in EHTMLElement</td></tr>
<tr><td>5/22/12</td><td>fix</td><td>tech</td><td></td><td>Fixed self-closing tag handling for all HTML elements</td></tr>
<tr border='1'><td>5/21/12</td><td><i>Release <b>3.1.4</b></i></td></tr>
<tr><td>5/21/12</td><td>fix</td><td>tech</td><td></td><td>Fixed NPE in AbstractHCTable</td></tr>
<tr border='1'><td>5/14/12</td><td><i>Release <b>3.1.3</b></i></td></tr>
<tr><td>5/14/12</td><td>add</td><td>tech</td><td></td><td>Improved toString implementations</td></tr>
<tr><td>5/11/12</td><td>fix</td><td>tech</td><td></td><td>Fixed out-of-band handling in HCHtml so that it is only called once per object, because otherwise the head may be modified in each consecutive call</td></tr>
<tr><td>5/11/12</td><td>fix</td><td>api</td><td></td><td>Fixed typo in method naming in HCBody and IHCOutOfBandNodeHandler (Ouf vs. Out)</td></tr>
<tr><td>5/11/12</td><td>fix</td><td>tech</td><td></td><td>Fixed error in out-of-band handling in HCHead</td></tr>
<tr border='1'><td>5/2/12</td><td><i>Release <b>3.1.2</b></i></td></tr>
<tr><td>4/27/12</td><td>fix</td><td>tech</td><td></td><td>Fixed potential crash when emitting a table without body rows</td></tr>
<tr><td>4/26/12</td><td>add</td><td>api</td><td></td><td>Added public static final constants in all HC<code>*</code> classes for non-null default values</td></tr>
<tr border='1'><td>4/26/12</td><td><i>Release <b>3.1.1</b></i></td></tr>
<tr><td>4/26/12</td><td>add</td><td>api</td><td></td><td>Added support for preselected values in HCSelect and HCOption</td></tr>
<tr><td>4/26/12</td><td>add</td><td>api</td><td></td><td>Using IHCRequestField(Boolean) as parameters in HC<code>*</code> input elements</td></tr>
<tr><td>4/26/12</td><td>add</td><td>api</td><td></td><td>Added IHCRequestField and IHCRequestFieldBoolean</td></tr>
<tr border='1'><td>4/23/12</td><td><i>Release <b>3.1.0</b></i></td></tr>
<tr><td>4/23/12</td><td>update</td><td>tech</td><td><b>yes</b></td><td>Updated to <a href='http://code.google.com/p/phloc-commons'>phloc-commons</a> 3.5.0</td></tr>
<tr><td>4/16/12</td><td>add</td><td>api</td><td></td><td>Added new API in HCSelect to specifically check for the selected options</td></tr>
<tr border='1'><td>3/14/12</td><td><i>Release <b>3.0.6</b></i></td></tr>
<tr><td>3/14/12</td><td>fix</td><td>api</td><td></td><td>Fixed implementation of HCDefinitionList</td></tr>
<tr><td>3/14/12</td><td>change</td><td>api</td><td></td><td>Made HCHtml non-final to allow for custom head and body implementations</td></tr>
<tr border='1'><td>3/4/12</td><td><i>Release <b>3.0.5</b></i></td></tr>
<tr><td>3/2/12</td><td>fix</td><td>tech</td><td></td><td>Fixed NPE for table rows without children in the debug version</td></tr>
<tr border='1'><td>2/29/12</td><td><i>Release <b>3.0.4</b></i></td></tr>
<tr><td>2/29/12</td><td>change</td><td>api</td><td></td><td>Made HCBody and HCHead non-final and added some getter</td></tr>
<tr><td>2/29/12</td><td>add</td><td>api</td><td></td><td>Added new method iterateTree in HCUtils</td></tr>
<tr border='1'><td>2/18/12</td><td><i>Release <b>3.0.3</b></i></td></tr>
<tr><td>2/18/12</td><td>add</td><td>api</td><td></td><td>Added dynamic input type for AbstractHCInput</td></tr>
<tr><td>2/18/12</td><td>add</td><td>api</td><td></td><td>Added common base class AbstractHCEdit for HCEdit, HCEditFile and HCEditPassword</td></tr>
<tr border='1'><td>2/17/12</td><td><i>Release <b>3.0.2</b></i></td></tr>
<tr><td>2/17/12</td><td>fix</td><td>tech</td><td></td><td>Fixed that checkboxes always get the default value assigned via the form customizer</td></tr>
<tr border='1'><td>2/16/12</td><td><i>Release <b>3.0.1</b></i></td></tr>
<tr><td>2/16/12</td><td>add</td><td>api</td><td></td><td>Added new base class AbstractHCInput for input elements</td></tr>
<tr><td>2/16/12</td><td>add</td><td>api</td><td></td><td>HCButton now has method setType</td></tr>
<tr><td>2/15/12</td><td>add</td><td>api</td><td></td><td>HCScript and HCScriptFile now have the possibility to handle a different script MIME type</td></tr>
<tr><td>2/13/12</td><td>fix</td><td>tech</td><td></td><td>Improved code generation for HTML5</td></tr>
<tr border='1'><td>2/13/12</td><td><i>Release <b>3.0.0</b></i></td></tr>
<tr border='1'><td>2/10/12</td><td><i>Release <b>3.0.0.RC3</b></i></td></tr>
<tr border='1'><td>2/5/12</td><td><i>Release <b>3.0.0.RC2</b></i></td></tr>
<tr border='1'><td>2/3/12</td><td><i>Release <b>3.0.0.RC1</b></i></td></tr>
<tr><td>2/3/12</td><td>change</td><td>api</td><td><b>yes</b></td><td>Now getAsNode and getAsXHTMLString take an HCConversionSettings object</td></tr>
<tr><td>2/2/12</td><td>change</td><td>api</td><td><b>yes</b></td><td>Restructured packages to avoid naming redundancies</td></tr>
<tr><td>2/1/12</td><td>add</td><td>api</td><td></td><td>Started improving support for HTML5</td></tr>
<tr border='1'><td>2/1/12</td><td><i>Release <b>2.8.0</b></i></td></tr>
<tr border='1'><td>1/29/12</td><td><i>Release <b>2.7.1</b></i></td></tr>
<tr><td>1/29/12</td><td>change</td><td>api</td><td><b>yes</b></td><td>Added possibility to specify a custom link type for HCLink using HCLinkTypeCustom</td></tr>
<tr border='1'><td>1/29/12</td><td><i>Release <b>2.7.0</b></i></td></tr>
</tbody></table>