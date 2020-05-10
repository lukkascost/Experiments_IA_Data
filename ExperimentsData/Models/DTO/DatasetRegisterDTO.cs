using System;
using System.Collections.Generic;

namespace ExperimentsData.Models.DTO
{
    public class DatasetRegisterDTO
    {
        public Guid Id { get; set; } 
        public DateTime CreatedAt { get; set; } 
        public DateTime UpdatedAt { get; set; } 
        public string description { get; set; }
        public string name { get; set; }
        public List<SampleRegisterDTO> Samples { get; set; }
    }
}