# Copyright 2011 robert tomb

class Greeting

	attr_reader :greetable

	def initialize(to_greet)
		@greetable = to_greet
	end

	def formal
		puts "hello, #{@greetable}\n"
	end

	def cool
		puts "yo, #{@greetable}\n"
	end

	def txt
		puts "LOL, ur 2 funny, #{@greetable}\n"
	end

	def renaissance
		puts "huzzah! #{@greetable}, knave...\n"
	end
end

