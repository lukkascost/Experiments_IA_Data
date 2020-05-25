using System;

namespace ExperimentsData.Models.DTO
{
    public class AttributeRegisterDto
    {
        public Guid Id { get; set; }
        public DateTime CreatedAt { get; set; } 
        public DateTime UpdatedAt { get; set; } 
        public string Value { get; set; }
        public string NormalizedValue { get; set; }
        public int order { get; set; }
        public string name { get; set; }
    }
}