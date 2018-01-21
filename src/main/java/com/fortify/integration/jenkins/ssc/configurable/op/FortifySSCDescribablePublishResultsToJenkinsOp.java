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
import java.io.PrintStream;

import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;

import com.fortify.client.ssc.connection.SSCAuthenticatingRestConnection;
import com.fortify.integration.jenkins.ssc.FortifySSCGlobalConfiguration;
import com.fortify.integration.jenkins.ssc.action.FortifySSCPublishAction;
import com.fortify.integration.jenkins.ssc.configurable.FortifySSCDescribableApplicationAndVersionName;

import hudson.EnvVars;
import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.Describable;
import hudson.model.Run;
import hudson.model.TaskListener;

public class FortifySSCDescribablePublishResultsToJenkinsOp extends AbstractFortifySSCDescribableOp {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default constructor
	 */
	@DataBoundConstructor
	public FortifySSCDescribablePublishResultsToJenkinsOp() {}
	
	/**
	 * Copy constructor
	 * @param other
	 */
	public FortifySSCDescribablePublishResultsToJenkinsOp(FortifySSCDescribablePublishResultsToJenkinsOp other) {
		if ( other != null ) {
			// TODO If we add any configurable properties
		}
	}
	
	@Override
	public void perform(FortifySSCDescribableApplicationAndVersionName applicationAndVersionNameJobConfig, Run<?, ?> run,
			FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
		PrintStream log = listener.getLogger();
		EnvVars env = run.getEnvironment(listener);
		final String applicationVersionId = applicationAndVersionNameJobConfig.getApplicationVersionId(env, log);
		SSCAuthenticatingRestConnection conn = FortifySSCGlobalConfiguration.get().conn();
		run.addOrReplaceAction(new FortifySSCPublishAction(run.getParent()));
	}
	
	@Symbol("publishResults")
	@Extension
	public static final class FortifySSCDescriptorPublishResultsToJenkinsOp extends AbstractFortifySSCDescriptorOp {
		static final String DISPLAY_NAME = "Publish results to Jenkins Dashboard";

		@Override
		public FortifySSCDescribablePublishResultsToJenkinsOp createDefaultInstanceWithConfiguration() {
			return new FortifySSCDescribablePublishResultsToJenkinsOp(getDefaultConfiguration());
		}
		
		@Override
		public FortifySSCDescribablePublishResultsToJenkinsOp createDefaultInstance() {
			return new FortifySSCDescribablePublishResultsToJenkinsOp();
		}
		
		@Override
		protected FortifySSCDescribablePublishResultsToJenkinsOp getDefaultConfiguration() {
			return (FortifySSCDescribablePublishResultsToJenkinsOp)super.getDefaultConfiguration();
		}
		
		@Override
		protected Class<? extends Describable<?>> getGlobalConfigurationTargetType() {
			return FortifySSCDescribablePublishResultsToJenkinsOp.class;
		}
		
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
