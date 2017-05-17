package me.danieleangelucci.main;

public class Main
{

	public static void main(String[] args)
	{
		
		checkCommandlineArgs(args);
		
		String categoriesFilePath = args[0];
		String inputFilePath = args[1];

		System.out.println("Welcome to \"Sales taxes problem\"!");
		System.out.println("Loading categories file: " + categoriesFilePath);
		System.out.println("Using input file: " + inputFilePath);

		
	}
	
	private static void checkCommandlineArgs(String[] args) {
		
		if(args.length == 2)
			return;

		if(args.length < 2)
	        System.err.println("Too few arguments.");
		if(args.length > 2)
	        System.err.println("Too many arguments.");
        System.err.println("Commandline should be:\njava -jar \"path_to_jar\" \"categories_filepath\" \"input_filepath\"");
		System.exit(1);
	}

}
