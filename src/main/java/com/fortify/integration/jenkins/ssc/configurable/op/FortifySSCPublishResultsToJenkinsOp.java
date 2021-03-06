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
package com.fortify.integration.jenkins.ssc.configurable.op;

import java.io.IOException;

import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;

import com.fortify.integration.jenkins.ssc.action.FortifySSCPublishAction;
import com.fortify.integration.jenkins.ssc.configurable.FortifySSCApplicationAndVersionName;

import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.Run;
import hudson.model.TaskListener;

public class FortifySSCPublishResultsToJenkinsOp extends AbstractFortifySSCOp {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor
	 */
	@DataBoundConstructor
	public FortifySSCPublishResultsToJenkinsOp() {}
	
	@Override
	protected void configureDefaultValuesAfterErrorHandler() {
		// TODO Once we start adding properties, initialize default values
	}
	
	@Override
	public void perform(FortifySSCApplicationAndVersionName applicationAndVersionNameJobConfig, Run<?, ?> run,
			FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
		//PrintStream log = listener.getLogger();
		//EnvVars env = run.getEnvironment(listener);
		//final String applicationVersionId = applicationAndVersionNameJobConfig.getApplicationVersionId(log, env);
		//SSCAuthenticatingRestConnection conn = FortifySSCGlobalConfiguration.get().conn();
		run.addOrReplaceAction(new FortifySSCPublishAction(run.getParent()));
	}
	
	@Symbol("publishResults")
	@Extension
	public static final class FortifySSCDescriptorPublishResultsToJenkinsOp extends AbstractFortifySSCDescriptorOp {
		static final String DISPLAY_NAME = "Publish results to Jenkins Dashboard";
		
		@Override
		public String getDisplayName() {
			// TODO Internationalize this
			return DISPLAY_NAME;
		}
		
		@Override
		public int getOrder() {
			return 1000;
		}
    }
}
