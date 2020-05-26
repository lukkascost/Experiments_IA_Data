using System;
using System.Collections.Generic;
using ExperimentsData.Models.DAO;
using ExperimentsData.Models.Enums;

namespace ExperimentsData.Models.DTO
{
    public class SampleRegisterDTO
    {
        public DateTime CreatedAt { get; set; } 
        public DateTime UpdatedAt { get; set; } 
        public ExtractorType ExtractorType { get; set; }
        public List<AttributeRegisterDto> Attributes { get; set; }
        public string OriginalFileName { get; set; }
        public string label { get; set; }
    }
}