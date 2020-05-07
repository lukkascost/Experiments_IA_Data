using ExperimentsData.Models.Enums;

namespace ExperimentsData.Models.DAO
{
    public class AttributeEntity : BaseEntity
    {
        
        public string Value { get; set; }
        public string NormalizedValue { get; set; }
        public int order { get; set; }
        public string name { get; set; }
    }
}