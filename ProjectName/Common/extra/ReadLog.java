// COMMANDS:
// Move, Rotate, Scale, Resize, Zoom
private final String move = "mov", rotate = "rot", scale = "scl", 
			resize = "rsz", zoom = "zom";

// SHAPES (Solids):
// Prisms: Rectangular, Triangular, & Hexagonal
// Pyramids: Square & Rectangular
// Cylinder
// Sphere
// Cone - We don't support this YET...
private final String recPris = "rec", triPris = "tri", hexPris = "tri", sqrPyr = "sqp",
			recPyr = "rep", cylinder = "cyl", sphere = "sph";

while (scanner.hasNextLine()) {
	String line = scanner.nextLine();
	
	// For all lines
	String cmd = line.substring(0, 3); // Command
	
	if (cmd == zoom) {
		double percent =  line.substring(3, line.length-1); // Percentage
	} else {	
		String shp = line.substring(3, 6); // Action
		
		if (cmd == move) {
			String[] seg = line.substring(6, line.length-1).split(",");
			double x = seg[0]; // X-Axis Translation
			double y = seg[1]; // Y-Axis Translation
			double z = seg[2]; // Z-Axis Translation
		} else if (cmd == rotate) {
			String axis = line.substring(6) // Which axis?
			double degrees = line.substring(7, line.length-1); // How much?
		} else if (cmd == scale) {
			double percent =  line.substring(6, line.length-1); // Percentage
		} else if (cmd == resize) {
			if (shp == recPris || shp == recPyr) {
				String[] seg = line.substring(6, line.length-1).split(",");
				double height = seg[0]; // X-Axis Translation
				double width = seg[1]; // Y-Axis Translation
				double length = seg[2]; // Z-Axis Translation
			} else if (shp == sqrPris || shp == sqrPyr || shp == hexPrism) {
				String[] seg = line.substring(6, line.length-1).split(",");
				double height = seg[0]; // X-Axis Translation
				double width = seg[1]; // Y-Axis Translation
			} else if (shp = sphere) {
				double radius = line.substring(6, line.length-1); // New radius
			} else if (shp = cylinder) {
				String[] seg = line.substring(6, line.length-1).split(",");
				double height = seg[0]; // Height
				double radius = seg[1]; // Radius
			}
		}
	}
	// [execute the command on the shape using the information retrieved from the line]
}