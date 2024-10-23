# Promo Code and Course System

## Overview
This project is a system designed to manage promo codes and associate them with courses. It allows discounts to be applied to courses and calculates the final discounted price when a promo code is applied. The system focuses on courses and promo codes only, with no user interaction.

## Features
- Create promo codes with discount percentages.
- Apply promo codes to specific courses.
- Validate promo code usage based on expiry dates and activation status.
- Calculate final prices after applying promo codes.

## Entities

### Promo Code
- **ID**: System-generated unique identifier.
- **Code**: Unique promo code (e.g., "SAVE20").
- **Discount Percent**: Discount percentage (e.g., 20%).
- **Is Active**: Boolean indicating whether the promo code is active.
- **Expiry Date**: Optional expiration date for the promo code.
- **Course ID**: Foreign key associating the promo code with a specific course.

### Course
- **ID**: Unique identifier for the course.
- **Name**: Name of the course (e.g., "Java Programming").
- **Price**: Original price of the course.

## Database Structure

### Promo Codes Table
- **ID**: Unique identifier.
- **Code**: Promo code string.
- **Discount Percent**: Percentage discount.
- **Is Active**: Indicates if the promo code is active.
- **Expiry Date**: Optional expiry date.
- **Course ID**: Foreign key linking to a course.

### Courses Table
- **ID**: Unique identifier.
- **Name**: Course name.
- **Price**: Original price of the course.

## API Endpoints

### 1. Create Promo Code
- **Endpoint**: `POST /api/promocode`
- **Request Body**:
  ```json
  {
    "code": "SAVE20",
    "discount_percent": 20,
    "expiry_date": "2024-12-31",
    "course_id": 1
  }

## Response Handling

### Success Response
- Returns:
  - Original price
  - Discount percentage
  - Discounted price

### Failure Response
- Error is returned if the promo code is:
  - Invalid
  - Expired
  - Not applicable to the course

## Business Rules

1. Promo codes can only be applied to the course they are associated with
2. Expired promo codes cannot be applied
3. Only active promo codes can be used
4. A course's price cannot be negative or zero after applying a promo code

## Example Scenarios

### Creating a Promo Code
A new promo code "SAVE20" is created with the following specifications:
- Discount: 20%
- Course: Java course (Price: $100)
- Status: Active
- Expiry: December 31, 2024

### Applying a Promo Code
When a customer applies the promo code "SAVE20" to the Java course:
- Original Price: $100
- Discount: 20% → $20
- Final Price: $80

### Promo Code Expiry
- System returns an error if promo code is used after its expiration date

### Inactive Promo Code
- System rejects any attempts to use inactive promo codes

## Security and Validations

- Promo codes must be unique to prevent duplication
- Discount percentages must be logical (1-100%)
- Promo codes can only be applied to their associated course
- Expired or inactive promo codes cannot be used

## Error Handling

### Invalid Promo Code
System returns an error for:
- Non-existent promo codes
- Inactive promo codes

### Expired Promo Code
- System returns an error for any expired promo codes



  
