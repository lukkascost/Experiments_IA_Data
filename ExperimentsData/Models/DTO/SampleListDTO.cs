using System;
using System.Collections.Generic;
using ExperimentsData.Models.DAO;
using ExperimentsData.Models.Enums;

namespace ExperimentsData.Models.DTO
{
    public class SampleListDTO
    {
        public Guid Id { get; set; } 
        public DateTime CreatedAt { get; set; } 
        public DateTime UpdatedAt { get; set; } 
        public ExtractorType ExtractorType { get; set; }
        public long Attributes { get; set; }
        public string OriginalFileName { get; set; }
        public long order { get; set; }
        public string label { get; set; }
    }
}