using System;
using System.Collections.Generic;
using ExperimentsData.Models.DAO;

namespace ExperimentsData.Repositories
{
    public interface IAttributeRepository
    {
        List<AttributeEntity> GetAllBySampleId(Guid sampleId);
        void Save(AttributeEntity entity);
    }
}