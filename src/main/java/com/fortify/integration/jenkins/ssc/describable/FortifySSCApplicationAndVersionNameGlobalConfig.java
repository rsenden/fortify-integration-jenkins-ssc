/*******************************************************************************
 * (c) Copyright 2017 EntIT Software LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a 
 * copy of this software and associated documentation files (the 
 * "Software"), to deal in the Software without restriction, including without 
 * limitation the rights to use, copy, modify, merge, publish, distribute, 
 * sublicense, and/or sell copies of the Software, and to permit persons to 
 * whom the Software is furnished to do so, subject to the following 
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be included 
 * in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY 
 * KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE 
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
 * IN THE SOFTWARE.
 ******************************************************************************/
package com.fortify.integration.jenkins.ssc.describable;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import hudson.Extension;

public class FortifySSCApplicationAndVersionNameGlobalConfig extends AbstractFortifySSCApplicationAndVersionNameConfig<FortifySSCApplicationAndVersionNameGlobalConfig> {
	@DataBoundConstructor
	public FortifySSCApplicationAndVersionNameGlobalConfig() {}
	
	/**
	 * Allow property to be configured in global config
	 */
	@Override @DataBoundSetter
	public void setApplicationNameOverrideAllowed(boolean applicationNameOverrideAllowed) {
		System.out.println("setApplicationNameOverrideAllowed: "+applicationNameOverrideAllowed);
		super.setApplicationNameOverrideAllowed(applicationNameOverrideAllowed);
	}
	
	/**
	 * Allow property to be configured in global config
	 */
	@Override @DataBoundSetter
	public void setVersionNameOverrideAllowed(boolean versionNameOverrideAllowed) {
		System.out.println("setVersionNameOverrideAllowed: "+versionNameOverrideAllowed);
		super.setVersionNameOverrideAllowed(versionNameOverrideAllowed);
	}
	
	@Extension
	public static final class FortifySSCApplicationAndVersionNameGlobalConfigDescriptor extends AbstractFortifySSCApplicationAndVersionNameConfigDescriptor<FortifySSCApplicationAndVersionNameGlobalConfig> {
		@Override
		public String getDisplayName() {
			// TODO Internationalize this
			return "Configure default application/version name";
		}
		
		@Override
		public FortifySSCApplicationAndVersionNameGlobalConfig createDefaultInstance() {
			return new FortifySSCApplicationAndVersionNameGlobalConfig();
		}
	}
	

}
