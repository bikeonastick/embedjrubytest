# Copyright 2011 robert tomb
require 'greeting'

	myArgs = ARGV.clone()
	if(myArgs.length == 0) 
		puts "no name given"
	else
		greeter = Greeting.new(ARGV[0])
		greeter.formal
		greeter.cool
		greeter.txt
		greeter.renaissance
	end


