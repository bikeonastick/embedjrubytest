package org.bikeonastick.scripting;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jruby.Ruby;
import org.jruby.RubyArray;
import org.jruby.RubyClass;
import org.jruby.ast.executable.Script;
import org.jruby.embed.ScriptingContainer;
import org.jruby.exceptions.RaiseException;
import org.jruby.internal.runtime.GlobalVariables;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.javasupport.JavaEmbedUtils.EvalUnit;
import org.jruby.runtime.builtin.IRubyObject;

/**
 * Copyright 2011 robert tomb
 */
public class RubyRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		RubyRunner theMaker = new RubyRunner("call_greeting.rb");
		
		theMaker.rubyEval(args);
		
	}
	
	public RubyRunner(String scriptName){
		this.scriptName = scriptName;
		this.fileUrl = this.getClass().getResource("/" + scriptName);
		System.out.println();
	}
	

	/**
	 * This creates a RubyRuntime with loadpaths set to the directory
	 * that holds your script.
	 */
	public Ruby getRubyRuntime(){
		if(this.rubyRuntime == null){
			List placesToSearch = new ArrayList();
			String filepath = getScriptLoc();
			placesToSearch.add(filepath);
			
			this.rubyRuntime = JavaEmbedUtils.initialize(placesToSearch);
		}
		
		return this.rubyRuntime;
	}
	
	public String getScriptLoc(){
		String loc = null;
		if(fileUrl != null){
			String filePath = fileUrl.getFile();
			loc = filePath.substring(0, (filePath.length() - this.scriptName.length()));
		}
		return loc;
	}
	
	public IRubyObject rubyEval(String[] args) {
		InputStream script;
		IRubyObject ret = null;
		
		try {
			
			script = this.fileUrl.openStream();
			ScriptingContainer container = new ScriptingContainer();
			container.setArgv(args);
			EvalUnit parsedScript = container.parse(script,this.scriptName);
			ret = parsedScript.run();
			
		} catch (IOException e) {
			System.out.println("Problems reading the ruby script");
			e.printStackTrace();
		}
		
		return ret;
	}
	
	private Ruby rubyRuntime;
	private URL fileUrl;
	private String scriptName;
}


