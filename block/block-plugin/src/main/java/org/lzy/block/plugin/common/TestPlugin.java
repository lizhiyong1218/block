package org.lzy.block.plugin.common;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * 
 * @goal lzytestPlugin
 * 
 * @phase process-sources
 */
public class TestPlugin extends AbstractMojo {

	public void execute() throws MojoExecutionException {
		 System.out.println("test plugin>>>>>>");
	}
}
