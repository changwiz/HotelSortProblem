Please implement a variety of sorting algorithms in a manner that 

would be included in a library for broad reuse; there is no need for a UI component for this exercise. We want to 

be able to exercise both ascending and descending sort orders. Your implementation should use the attached 

JSON data set, a sorting criteria option, and a sort order. Your solution should return an appropriately ordered 

collection of data objects

Your submission should be able to return the hotels in the data set sorted by any of the following sorting criteria: 

 Distance from location center 

 Lowest price 

 Average user rating 

o For this exercise, we'll use Euclidean distances calculated by Pythagorean theorem 

o As there are potentially multiple rates per property, lowest price sorted hotel results should sort 

hotels by the lowest rate available for that property. 

o Calculated as the algebraic "mean" of user ratings
