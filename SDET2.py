import math

# Superclass: Circle
class Circle:
    def __init__(self, radius):
        self.radius = radius

    def getArea(self):
        # Area of the circle: π * radius^2
        return math.pi * (self.radius ** 2)

# Subclass: Cylinder (inherits from Circle)
class Cylinder(Circle):
    def __init__(self, radius, height):
        # Call the constructor of the superclass (Circle) to initialize the radius
        super().__init__(radius)
        self.height = height

    # Overriding getArea method to calculate the area of the base of the cylinder
    def getArea(self):
        # Area of the base of the cylinder (circle's area): π * radius^2
        return math.pi * (self.radius ** 2)

    def getVolume(self):
        # Volume of the cylinder: base area * height
        return self.getArea() * self.height

# Create an object of Cylinder class
cylinder = Cylinder(radius=5, height=10)

# Get and display the area of the base (circle) and the volume of the cylinder
print(f"Area of the base of the cylinder: {cylinder.getArea():.2f} square units")
print(f"Volume of the cylinder: {cylinder.getVolume():.2f} cubic units")
